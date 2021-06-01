/**
 * Created by lfp on 2021/3/19.
 */


/*
 * public static void main(String [] args){....}
 * public：公共的，访问权限最大，由于main要被jvm调用，所以要权限够大
 * static：静态不需要创建对象，方便jvm调用
 * void：方法的返回值给调用者，返回给jvm没有意义
 * main：方法的入口
 * String[]args：字符串数组，那么值呢？
 * 					早期为了键盘录入
 * 					格式是：java mainDemo helloworld java
 */

package testpool;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class HttpClientLongConnTest {
    public static void main(String args[]) throws Exception {

    try (CloseableHttpClient httpClient = HttpClients.createDefault()){
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            System.out.println(httpResponse.getCode() + " " + httpResponse.getReasonPhrase());
            HttpEntity httpEntity = httpResponse.getEntity();
            System.out.println(httpEntity.getContent().read());
            System.out.println(httpResponse.getHeaders().length);
            EntityUtils.consume(httpEntity);


        } catch (Exception e) {
            System.out.println("error"+e);

        }

    }catch (Exception a) {
        System.out.print(a);
    }

    Content content = Request.get("https://www.baidu.com").execute().returnContent();
    System.out.println(content);


    };
}


