package org.example.core;

import org.example.common.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    public Object sendRpcRequest(RpcRequest rpcRequest, String host, int port){
        try(Socket socket = new Socket(host, port);) {
            //获取socket的输出流，用一个对象输出流包装，专门用于序列化
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            //同理，通过输入流获取返回的参数
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
