package org.example.core;

import org.example.common.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//为了屏蔽一些无关的操作，用动态代理来对server的方法进行一个包装
//需要实现InvocationHandler接口，这是提供调用方法的重要一步
public class ClientProxy implements InvocationHandler {
    private String host;
    private int port;

    public ClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    //该动态代理类需要返回一个示例,使用泛型，根据传入的类返回
    public <T> T getProxy(Class<T> clazz) {
        //固定方法，返回一个动态代理对象
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, ClientProxy.this);
    }

    //该处理方法就是将用户调用的方法封装成请求发送到指定服务
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //这里就可以进行AOP编程了
        //由于请求的多样化，采用builder设计模式设计请求
        //getDeclaringClass()返回声明的类，也就是接口。不会返回匿名类
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameters(args)
                .paramTypes(method.getParameterTypes())
                .build();
        Client client = new Client();
        return client.sendRpcRequest(rpcRequest, host, port);
    }
}
