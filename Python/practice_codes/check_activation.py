# coding: utf-8

from datetime import timedelta
import datetime
from email.mime.text import MIMEText
from ftplib import FTP
import getopt
from os import path
import os
import smtplib  
import sys
import time

import MySQLdb
import pysftp


FTP_HOST = "59.151.16.190"
FTP_PORT = "22"
FTP_USR = "msloan"
FTP_PASSWD = "xsYbRXS2VrgBTP0e"
FTP_DIRNAME = "/loanfile"
LOCAL_SAVE_PATH = "./check_saved"

# FTP_HOST = "192.168.2.61"
# FTP_PORT = "22"
# FTP_USR = "qunar01"
# FTP_PASSWD = "AOVSpneuLJkytUZ4Th0w2Sj4ZH9Z6M"
# FTP_DIRNAME = "/upload"

EXCHANGE_URL = "mail.msxf.com"
EXCHANGE_USER = "chao.su"
EXCHANGE_PASSWORD = "Sc54612185"
EXCHANGE_FROM = "chao.su@msxf.com"
MAIL_FORM = "去哪儿拿去花对账<%s>" % EXCHANGE_FROM
MAIL_TO_IIST = ["chao.su@msxf.com", "hongzheng.liu@msxf.com", "peng.wu@msxf.com", "fei.he@msxf.com", "wanjin.jiang@msxf.com"]
#MAIL_TO_IIST = ["chao.su@msxf.com"]

# DB_HOST = "127.0.0.1"
# DB_PORT = 3306
# DB_USER = "root"
# DB_PASS = "root"

DB_HOST = "open.msxf.dbtest"
DB_PORT = 3306
DB_USER = "openrw"
DB_PASS = "openrw111111"

CREDIT_FILE = "credit"
ACTIVATE_FILE = "activate"
PAYF_FILE = "payInfo"
REFUND_FILE = "refundInfo"
REPAY_FILE = "repaymentInfo"

ENUM_ACTIVE = "激活"
ENUM_PAY = "贷款"
ENUM_REPAY = "还款"
ENUM_REFUND = "退款"

def format_gentime(tm):
    return tm.strftime("%Y-%m-%d %H:%M:%S")

def amount_tostr(amount):
    return "%d.%02d" % (amount / 100, amount % 100)        

def str_toamount(val):
    val = val.strip()
    if "." not in val:
        return int(val) * 100
    else:
        val = val + "00"
        i = val.index(".")
        return int("0" + val[:i]) * 100 + int(val[i+1:][:2]) 
        
                

class Check:
    def __init__(self, dt, db_conn):
        self.date_time = dt
        self.conn = db_conn
        self.error_line = []
        self.error_gen = []
        self.check_count = {}
        self.loan_sum = 0
        self.repay_sum = 0
        self.refund_sum = 0
    
    def get_check_file(self, name):
        return "%s_MSLOAN_%s.txt" % ((self.date_time - timedelta(days=1)).strftime("%Y-%m-%d"), name) 
    
    def get_today_range(self):
        return ((self.date_time - timedelta(days = 1)).strftime("%Y-%m-%d 00:00:00"), self.date_time.strftime("%Y-%m-%d 00:00:00"))
    
    def do_sum(self):
        self.loan_sum = self._get_sum_loan()
        self.repay_sum = self._get_sum_repay()
        self.refund_sum = self._get_sum_refund()
        
    def do_check(self):
        active_file = self.get_check_file(ACTIVATE_FILE)
        pay_file = self.get_check_file(PAYF_FILE)
        repay_file = self.get_check_file(REPAY_FILE)
        refund_file = self.get_check_file(REFUND_FILE)
        
        print "do_check active"
        if not sftp_get_file(active_file):
            self.error_gen.append("get active file %s failed" % (active_file))
        else:
            self.check_count[ENUM_ACTIVE] = self.check_wrap(active_file, self.check_active)
    
        print "do_check pay"
        if not sftp_get_file(pay_file):
            self.error_gen.append("get active file %s failed" % (pay_file))
        else:
            self.check_count[ENUM_PAY] = self.check_wrap(pay_file, self.check_pay)
            
        print "do_check repay"
        if not sftp_get_file(repay_file):
            self.error_gen.append("get repayment file %s failed" % (repay_file))
        else:
            self.check_count[ENUM_REPAY] = self.check_wrap(repay_file, self.check_repay)
        
        print "do_check refund"
        if not sftp_get_file(refund_file):
            self.error_gen.append("get refund file %s failed" % (refund_file))
        else:
            self.check_count[ENUM_REFUND] = self.check_wrap(refund_file, self.check_refund)
            

    def check_wrap(self, file, func):
        count = 0
        line_count = 0
        try:
            f = open(os.path.join(LOCAL_SAVE_PATH, file))
            idx = 0
            for line in f:
                idx += 1
                if line.startswith("H|"):
                    date = line.split("|")[1]
                elif line.startswith("F|"):
                    line_count = int(line.split("|")[1])
                else:
                    if not line.startswith("D|"):
                        print "line %d:%s: -- not valid" % (idx, line)
                        self.error_gen.append("line %d for file %s seems not vailid:%s" % (idx, file, line))
                        continue
                    
                    func(idx, line)
                    count += 1
            f.close()
            return (count, line_count)
        except Exception, e:
            print str(e)
            return (count, line_count)
            
    def _get_active_record(self, apply_no):
        try:
            cur = self.conn.cursor(cursorclass = MySQLdb.cursors.DictCursor)
            cur.execute("select * from open.qunar_credit where qunar_apply_no='%s'" % apply_no)
            r = cur.fetchone()
            return True, r
        except Exception, e:
            print "query credit rcord faild:%s" % (str(e))
            
    def _get_sum_loan(self):
        try:
            cur = self.conn.cursor(cursorclass = MySQLdb.cursors.DictCursor)
            cur.execute("select sum(loan_amount) from open.qunar_loan where loan_time >='%s' and loan_time < '%s'" % self.get_today_range())
            r = cur.fetchone()
            return r["sum(loan_amount)"] if r["sum(loan_amount)"] != None else 0
        except Exception, e:
            print "_get_sum_loan rcord faild:%s" % (str(e))
            return -1
            
    def _get_sum_repay(self):
        try:
            cur = self.conn.cursor(cursorclass = MySQLdb.cursors.DictCursor)
            cur.execute("select sum(repay_amount) from open.qunar_loan_repay where request_time >='%s' and request_time < '%s'" % self.get_today_range())
            r = cur.fetchone()
            return r["sum(repay_amount)"] if r["sum(repay_amount)"] != None else 0
        except Exception, e:
            print "_get_sum_repay rcord faild:%s" % (str(e))
            return -1
            
    def _get_sum_refund(self):
        try:
            cur = self.conn.cursor(cursorclass = MySQLdb.cursors.DictCursor)
            cur.execute("select sum(refund_amount) from open.qunar_loan_refund where refund_time >='%s' and refund_time < '%s'" % self.get_today_range())
            r = cur.fetchone()
            return r["sum(refund_amount)"] if r["sum(refund_amount)"] != None else 0
        except Exception, e:
            print "_get_sum_refund rcord faild:%s" % (str(e))
            return -1
    
    def _get_loan_record(self, loan_no):
        try:
            cur = self.conn.cursor(cursorclass = MySQLdb.cursors.DictCursor)
            cur.execute("select * from open.qunar_loan where qunar_loan_no='%s'" % loan_no)
            r = cur.fetchone()
            return True, r
        except Exception, e:
            print "query loan rcord faild:%s" % (str(e))
            
            
    def _get_refund_record(self, loan_no):
        try:
            cur = self.conn.cursor(cursorclass = MySQLdb.cursors.DictCursor)
            cur.execute("select * from open.qunar_loan_refund where refund_no='%s'" % loan_no)
            r = cur.fetchone()
            return True, r
        except Exception, e:
            print "query refund_no rcord faild:%s" % (str(e))
            
    def _get_repay_record(self, repay_no):
        try:
            cur = self.conn.cursor(cursorclass = MySQLdb.cursors.DictCursor)
            cur.execute("select * from open.qunar_loan_repay where repayment_no='%s'" % repay_no)
            r = cur.fetchone()
            return True, r
        except Exception, e:
            print "query repayment_no rcord faild:%s" % (str(e))
            

    def check_active(self, idx, line):
        lst = line.split("|")
        apply_no = lst[1]
        contract_no = lst[2]
        start_time = lst[3]
        state = lst[4] #2 success
        credit_amt = lst[5]
        rate = lst[6]
        qunar_user_id = lst[7]
        user_name = lst[8]
        #multi_rate = lst[9]
        
        ok, credit = self._get_active_record(apply_no)
        if not ok:
            self.error_line.append(("ACTIVE", idx, line, "get active_recored failed"))
            print "get_active_recored failed"
            
        if not credit:
            self.error_line.append(("ACTIVE", idx, line, "ACTIVE RECORED FOOUND"))
            print "active record not found for %d:%s" % (idx, line)
            return
        
        #3-已激活
        if state == "2" and credit["status"] != 3:
            self.error_line.append(("ACTIVE", idx, line, "ACTIVE_FAILED")) 
    
        if credit["credit_amount"] != str_toamount(credit_amt):
            self.error_line.append(("ACTIVE", idx, line, "credit_amt value not match"))
            print "credit_amt value not match %d:%s" % (idx, line)
            return
    
    def check_pay(self, idx, line):
        lst = line.split("|")
        msxf_no = lst[1]
        loan_no = lst[2]
        loan_time = lst[3]
        loan_amount = lst[4]
        term_no = lst[5]
        status = lst[6] # 1-success
        qunar_user_id = lst[7]
        user_name = lst[8]
        payed_fee = lst[9]
        rate = lst[10]
        
        ok, loan = self._get_loan_record(loan_no)
        if not ok:
            self.error_line.append(("LOAN", idx, line, "get loan_record failed"))
            print "get loan record failed"
            return
        
        if not loan:
            self.error_line.append(("LOAN", idx, line, "RECORD NOT FOOUND"))
            print "loan record not found for %d:%s" % (idx, line)
            return
        
        if status == "1" and loan["status"] != 2:
            self.error_line.append(("LOAN", idx, line, "LOAN STATUS NOT MATCH"))
            print "laon status not match for %d:%s" % (idx, line) 
            
        if loan["loan_amount"] != str_toamount(loan_amount) or \
            loan["term_no"] != int(term_no) or \
            loan["fee_amount"] != str_toamount(payed_fee):
            self.error_line.append(("LOAN", idx, line, "loan_amount/term_no/feee_amount value not match"))
            print "loan value not match %d:%s" % (idx, line)
            return

    def check_refund(self, idx, line):
        lst = line.split("|")
        loan_no = lst[1]
        refund_no = lst[2]
        refund_time = lst[3]
        refund_amount = lst[4]
        refund_status = lst[5] # S-success
        qunar_user_id = lst[6]
        user_name = lst[7]
        return_capital = lst[8]
        return_fee = lst[9]
        return_fine = lst[10]
        
        ok, refund = self._get_refund_record(refund_no)
        if not ok:
            self.error_line.append(("REFUND", idx, line, "get refund failed"))
            print "get refund record failed"
            return
        
        if not refund:
            self.error_line.append(("REFUND", idx, line, "REFUND NOT FOOUND"))
            print "refund record not found for %d:%s" % (idx, line)
            return
        
        if refund_status == "S" and refund["status"] != 2:
            self.error_line.append(("REFUND", idx, line, "REFUND STATUS NOT MATCH"))
            print "refund status not match for %d:%s" % (idx, line) 
            
        if refund["return_amount"] != str_toamount(refund_amount) or \
            format_gentime(refund["refund_time"]) != refund_time:
            self.error_line.append(("REFUND", idx, line, "return_amount/refund_time value not match"))
            print "refund value not match %d:%s" % (idx, line)
            return

    def check_repay(self, idx, line):
        lst = line.split("|")
        loan_no = lst[1]
        repay_no = lst[2]
        repay_time = lst[3]
        repay_type = lst[4]
        repay_term = lst[5]
        repay_amount = lst[6]
        repay_status = lst[7] # S - success
        qunar_user_id = lst[8]
        user_name = lst[9]
        repay_capital = lst[10]
        repay_fee = lst[11]
        repay_fine = lst[12]
        
        ok, repay = self._get_repay_record(repay_no)
        if not ok:
            self.error_line.append(("REPAY", idx, line, "get repay failed"))
            print "get repay record failed"
            return
        
        if not repay:
            self.error_line.append(("REPAY", idx, line, "REPAY NOT FOOUND"))
            print "repay record not found for %d:%s" % (idx, line)
            return
        
        if repay_status == "S" and repay["status"] != 2:
            self.error_line.append(("REPAY", idx, line, "REPAY STATUS NOT MATCH"))
            print "repay status not match for %d:%s" % (idx, line) 
            
        if repay["repay_amount"] != str_toamount(repay_amount) or \
            format_gentime(repay["request_time"]) != repay_time:
            self.error_line.append(("REPAY", idx, line, "repay_amount/repay_time value not match"))
            print "repay value not match %d:%s" % (idx, line)
            return

    def formt_mail(self):
        rs = '''
        <h2>对账总数</h2>
        '''
        rs += '''
        <table style="width:600px;" cellpadding="2" cellspacing="0" border="1" bordercolor="#000000">
        <tbody>
        <tr>
            <td>
                <strong>类型</strong>
            </td>
            <td>
                <strong>对账数目</strong>
            </td>
            <td>
                <strong>数据总数</strong>
            </td>
        </tr>
        '''
        print self.check_count
        for (k, v) in self.check_count.items():
            rs += '''
            <tr>
                <td>%s</td>
                <td>%d</td>
                <td>%d</td>
            </tr>
            ''' % (k, v[0], v[1])
            
        rs += '''</tbody></table></br>'''
        
        
        rs += '''
        <strong>通用错误</strong>
         <table style="width:600px;" cellpadding="2" cellspacing="0" border="1" bordercolor="#000000">
        <tbody>
        '''
        for it in self.error_gen:
            rs += '''
            <tr>
                <td>%s</td>
            </tr>
            ''' % (it)
            
        rs += '''</tbody></table></br>'''
        
        rs += '''
        <h2>对账错误</h2>
         <table style="width:600px;" cellpadding="2" cellspacing="0" border="1" bordercolor="#000000">
        <tbody>
        <tr>
            <td>
                <strong>对账数据类型</strong>
            </td>
            <td>
                <strong>出错行</strong>
            </td>
            <td>
                <strong>行内容</strong>
            </td>
            <td>
                <strong>错误类型</strong>
            </td>
        </tr>
        '''
        for it in self.error_line:
            rs += '''
            <tr>
                <td>%s</td>
                <td>%d</td>
                <td>%s</td>
                <td>%s</td>
            </tr>
            ''' % (it[0], it[1], it[2], it[3])
            
        rs += '''</tbody></table></br>'''
            
            
        rs += '''
        <h2>总额统计</h2>
        <table style="width:600px;" cellpadding="2" cellspacing="0" border="1" bordercolor="#000000">
        <tbody>
        <tr>
            <td>
                <strong>类型</strong>
            </td>
            <td>
                <strong>总额</strong>
            </td>
        </tr>
        '''
        print [self.loan_sum, self.repay_sum, self.refund_sum]
        for it in [("当日贷款总额", self.loan_sum), ("当日还款总额", self.repay_sum), ("当日退款总额", self.refund_sum)]:
            rs += '''
            <tr>
                <td>%s</td>
                <td>%s 元</td>
            </tr>
            ''' % (it[0], amount_tostr(it[1]))
            
        rs += '''</tbody></table></br>'''
            
        return rs
    
    def send_result(self):
        sub = "去哪儿拿去花对账：%s" % (self.date_time.strftime("%Y-%m-%d"))
        for i in xrange(5):
            try:
                send_exchage_mail(MAIL_TO_IIST, MAIL_FORM, sub, self.formt_mail())
                return
            except Exception, e:
                print "send mail failed:%s" % str(e)


def get_check_file(file_name):
    try:
        ftp = FTP(host= FTP_HOST, user=FTP_USR, passwd=FTP_PASSWD)
        ftp.cwd(FTP_DIRNAME)
        local_file = open(file_name, "wb")
        ftp.retrbinary('RETR %s' % file_name, local_file.write)
        ftp.close()
    except Exception, e:
        print str(e)

def sftp_get_file(file_name):
    try:
        sftp = pysftp.Connection(host = FTP_HOST, username=FTP_USR, password= FTP_PASSWD)
        sftp.cwd(FTP_DIRNAME)
        sftp.get(file_name, localpath = os.path.join(LOCAL_SAVE_PATH, file_name))
        return True
    except Exception, e:
        print "get file %s error: %s" % (file_name, str(e))
        return False
    
    

def send_exchage_mail(to_list, me, sub, content):
    msg = MIMEText(content,  _subtype='html', _charset='utf8')
    msg['Subject'] = sub
    msg['From'] = me
    msg['To'] = ";".join(to_list)
    try:
        conn = smtplib.SMTP(EXCHANGE_URL, 587)
        conn.starttls()
        conn.login(EXCHANGE_USER, EXCHANGE_PASSWORD)
        conn.sendmail(me, to_list, msg.as_string())
        conn.close()
        return True
    except Exception, e:
        print str(e)
        return False

if __name__ == "__main__":
    help = '''
    -d  --date    check date(20160516)
    '''
    
    cur_time = datetime.datetime.now()
    time.clock()
    
    options, args = getopt.getopt(sys.argv[1:], "hd:", ["help", "date="])
    dt = ""  
    for key, val in options:
        if key in ("-d", "--date"):
            dt = val
    
    if dt != "":
        cur_time = datetime.datetime.strptime(dt, "%Y%m%d")
        
    print "check date:%s"  % str(cur_time)
            
    if not path.exists(LOCAL_SAVE_PATH):
        os.mkdir(LOCAL_SAVE_PATH)
        
    db = MySQLdb.connect(host = DB_HOST, port = DB_PORT, user = DB_USER, passwd = DB_PASS)
   
    check = Check(cur_time, db)
    check.do_check()
    check.do_sum()
    check.send_result()
    db.close()
     
    print "cost:%f" % (time.clock())

