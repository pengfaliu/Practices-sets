/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package main.cn.paul.timerDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lfp on 2020/12/4.
 */
public class MyTimer {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        myTask.run();

        Timer timer = new Timer();
        timer.schedule(new MyTask(),1000,100); //1秒后执行,并且每隔100ms重复执行

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer.cancel();
        timer.purge();
    }


}

class MyTask extends TimerTask {
    @Override
    public void run() {
        Format format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS");
        System.out.println("当前时间:" + format.format(new Date()));
        System.out.println(System.currentTimeMillis());
    }

    public List<String[]> getnews() {

        List<String[]> newslist = new ArrayList<String[]>();
        try {
            URL url = new URL("http://www.chinanews.com/scroll-news/news1.html");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);

            if (httpURLConnection.getResponseCode() == 200 ) {
                InputStream inputStream = url.openStream();
                // 注意编码问题，因为目标网页用的是gb2312，因此我这里也设置成gb2312，不然容易导致乱码
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"gb2312"));


            }

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }




        return newslist;
    }
}

