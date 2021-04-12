package org.example.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        try {
            //向本地8888的服务器发送信息
            Socket socket = new Socket("localhost",8888);
            //将控制台的信息发送给服务器
            Scanner scanner = new Scanner(System.in);
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
            //一定要加，否则无法正常输出信息
            socket.shutdownOutput();
            String reply = bufferedReader.readLine();
            System.out.println(reply);
            printWriter.close();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            //socket关闭的时候输入输出流都会自动关闭
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
