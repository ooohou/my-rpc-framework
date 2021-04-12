package org.example.core;

import org.example.common.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//为了屏蔽一些无关的操作，用动态代理来对server的方法进行一个包装
//需要实现InvocationHandler接口，这是提供调用方法的重要一步
public class ServerProxy implements InvocationHandler {
    private String host;
    private int port;

    public ServerProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    //该动态代理类需要返回一个示例,使用泛型，根据传入的类返回
    public <T> T getProxy(Class<T> clazz) {
        //固定方法，返回一个动态代理对象
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //这里就可以进行AOP编程了
        //由于请求的多样化，采用builder设计模式设计请求
        //getDeclaringClass()返回声明的类，也就是接口。不会返回匿名类
        RpcRequest rpcRequest = new RpcRequest().builder()
                .setInterface(method.getDeclaringClass().getName())
                .setMethodName(method.getName())
                .setParameters(args)
                .build();
        Client client = new Client();
        client.sendRpcRequest(rpcRequest, this.host, this.port);
        return rpcRequest;
    }
}
