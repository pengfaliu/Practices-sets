import apiProject as ap


class WeChat:

    def __init__(self):
        self.conf = ap.HandleConf()
        self.read = ap.HandleFiles()
        ap.urllib3.disable_warnings()

    @staticmethod
    def message(project, end, total, passing, succeed, failing, skip, error, address):
        """
        需要发送到企业微信的文案信息
        :param project:         项目名称
        :param end:             指定端[web、app、h5]
        :param total:           总计
        :param passing:         通过率
        :param succeed:         通过数
        :param failing:         失败数
        :param skip:            跳过数
        :param error:           错误数
        :param address:         链接地址->放一个链接信息
        :return:                返回data信息
        """
        data = {
            "msgtype": "markdown",  # 消息类型，此时固定为markdown
            "markdown": {
                "content": "# **提醒！自动化测试反馈**\n#### **请相关同事注意，及时跟进！**\n"
                "> 项目名称：<font color=\"info\">%s</font> \n"
                           "> 项目指定端：<font color=\"info\">%s</font> \n"
                           "> 测试用例总数：<font color=\"info\">%s条</font>；测试用例通过率：<font color=\"info\">%s</font>\n"
                           "> **--------------------运行详情--------------------**\n"
                           "> **成功数：**<font color=\"info\">%s</font>\n**失败数：**<font color=\"warning\">%s</font>\n"
                           "> **跳过数：**<font color=\"info\">%s</font>\n**错误数：**<font color=\"comment\">%s</font>\n"
                           "> ##### **报告链接：** [jenkins报告,请点击后进入查看](%s)" % (
                               project, end, total, passing, succeed, failing, skip, error, address)
                           # 加粗：**需要加粗的字**
                # 引用：> 需要引用的文字
                # 字体颜色(只支持3种内置颜色)
                # 标题 （支持1至6级标题，注意#与文字中间要有空格）
                # 绿色：info、灰色：comment、橙红：warning
            }
        }
        return data

    def send_message(self, project, end, total, passing, succeed, failing, skip, error, address='', is_curl=False):
        """
        发送文案信息
        :param project:         项目名称
        :param end:             指定端[web、app、h5]
        :param total:           总计
        :param passing:         通过率
        :param succeed:         通过数
        :param failing:         失败数
        :param skip:            跳过数
        :param error:           错误数
        :param address:         链接地址->放一个链接信息
        :param is_curl:         判断是测试还是线上
        :return:
        """
        jenkins_address = self.jenkins_data()
        data = self.message(project, end, total, passing, succeed, failing, skip, error, address=jenkins_address)
        # 获取企业微信群机器人的url, 使用的python第三方库requests库发送的请求
        if is_curl:
            url = self.conf.get_value('WeChat', 'pro_curl')
            res = ap.requests.post(url=url, json=data, verify=False)
        else:
            url = self.conf.get_value('WeChat', 'test_curl')
            res = ap.requests.post(url=url, json=data, verify=False)
        return res.text

    def jenkins_data(self):

        # 登录jenkins,从jenkins获取报告信息
        account = self.read.read_yaml('jenkins_data', 'pro')
        server = ap.jenkins.Jenkins(account['jenkins_url'], username=account['user_name'], password=account['password'])
        jenkins_data = tuple(i['url'] for i in server.get_views())
        for i in jenkins_data:
            u = ap.urlparse(i)
            v_scheme, v_netloc, v_path = u.scheme, u.netloc, u.path
            if '9first_api_all' in v_path:
                return i
            else:
                return v_scheme + '://' + v_netloc + '/'
            
if __name__ == '__main__':
    