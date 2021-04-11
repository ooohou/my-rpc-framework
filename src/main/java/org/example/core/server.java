package org.example.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
//server类包含服务器的属性和方法
public class server {
    public void register(Object service, int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("server启动...");
            //serverSocket接受的是连接过来的套接字
            Socket accept = serverSocket.accept();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
            objectOutputStream.write(666);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
