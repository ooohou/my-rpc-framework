package org.example.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        //java习惯捕获异常，这样比较方便处理
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            //目前只能做到一次接受一个客户端信息，因此用循环进行接受
            //开启服务器，准备接受信息
            Socket acceptSocket = serverSocket.accept();

            InputStream inputStream = acceptSocket.getInputStream();
            //为了接受字符串，使用Reader包装
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            PrintWriter printWriter = new PrintWriter(acceptSocket.getOutputStream());
            String message = bufferedReader.readLine();
            System.out.println(message);
            String reply = "我接受到了你的信息";
            System.out.println(reply);
            printWriter.write(reply);
            printWriter.flush();
            printWriter.close();
            acceptSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
