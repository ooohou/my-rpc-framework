package org.example.core;

import org.example.common.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public Object sendRpcRequest(RpcRequest rpcRequest, String host, int port){
        try {
            Socket socket = new Socket(host, port);
            //获取socket的输出流，用一个对象输出流包装，专门用于序列化
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            //同理，通过输入流获取返回的参数
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectInputStream.close();
            Object result = objectInputStream.readObject();
            objectInputStream.close();
            //可以用try-with-resource优化
            return result;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {

        }
        return null;
    }
}
