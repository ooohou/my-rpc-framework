package org.example.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        //使用try-with-resource,不需要手动关闭资源
        try(Socket socket = new Socket("localhost",8888);
            Scanner scanner = new Scanner(System.in)) {
            //向本地8888的服务器发送信息
            //输入流
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //输出流
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            String message = scanner.nextLine();
            printWriter.write(message);
            printWriter.flush();
            //关闭输入流
            socket.shutdownOutput();
            String reply = bufferedReader.readLine();
            System.out.println(reply);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
