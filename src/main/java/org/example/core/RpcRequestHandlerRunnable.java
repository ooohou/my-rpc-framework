package org.example.core;

import org.example.dto.RpcRequest;
import org.example.dto.RpcResponse;
import org.example.serviceRegistry.IServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class RpcRequestHandlerRunnable implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RpcRequestHandlerRunnable.class);
    //这个类主要是在特定端口的特定服务上调用方法。
    private RpcRequestHandler rpcRequestHandler;
    private Socket socket;
    //用于查找服务
    private IServiceRegistry serviceRegistry;

    public RpcRequestHandlerRunnable(Socket socket, RpcRequestHandler rpcRequestHandler, IServiceRegistry serviceRegistry){
        this.rpcRequestHandler = rpcRequestHandler;
        this.socket = socket;
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void run() {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            String serviceName = rpcRequest.getInterfaceName();
            Object service = serviceRegistry.getService(serviceName);
            Object result = rpcRequestHandler.handle(rpcRequest, service);
            objectOutputStream.writeObject(RpcResponse.success(result));
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
