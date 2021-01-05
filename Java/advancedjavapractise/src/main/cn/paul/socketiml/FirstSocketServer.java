/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package main.cn.paul.socketiml;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by lfp on 2020/11/24.
 */
public class FirstSocketServer  extends Thread {

    public ServerSocket serverSocket;

    public static void main(String[] args) {

        try
        {
            int port = Integer.parseInt(args[0]);
            Thread t = new FirstSocketServer(port);
            t.run();
        } catch (ArrayIndexOutOfBoundsException s){
            System.out.println("缺少参数");
            System.exit(1);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public FirstSocketServer(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run () {
        while (true) {
            try {
                System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodbye!");
                out.write(200);
                server.close();

            }
            catch (SocketTimeoutException e) {
                System.out.print("等待连接超时, ");
                //break;
            }
            catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
