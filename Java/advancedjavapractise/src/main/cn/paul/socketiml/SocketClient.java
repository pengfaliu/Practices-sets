/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package main.cn.paul.socketiml;

import java.io.*;
import java.net.Socket;

/**
 * Created by lfp on 2020/11/24.
 */
public class SocketClient {

    public static void main(String[] args) {

        try {
            String serverName = args[0];
            int port = Integer.parseInt(args[1]);

            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
            Socket socketClient = new Socket(serverName,port);
            System.out.println("远程主机地址：" + socketClient.getRemoteSocketAddress());
            OutputStream outToServer = socketClient.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);


            out.writeUTF("hello,from liu" + socketClient.getLocalAddress());
            InputStream inFromServer = socketClient.getInputStream();
            DataInputStream inputStream = new DataInputStream(inFromServer);
            System.out.println("服务器响应:" + inputStream.readUTF() + " : " +
                    inputStream.read() );
            socketClient.close(); //关闭连接


        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //e.printStackTrace();
            //e.printStackTrace();
            System.out.println("请输入需要连接的主机和端口!");
        }
        finally {
            System.out.println("再见咯!");
        }
    }
}
