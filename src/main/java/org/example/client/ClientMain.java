package org.example.client;

import org.example.pojo.Hello;
import org.example.core.ClientProxy;
import org.example.service.IHelloService;

public class ClientMain {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("localhost", 8888);
        IHelloService proxy = clientProxy.getProxy(IHelloService.class);
        String hello = proxy.sayHello(new Hello("cc", "this is a demo"));
        System.out.println(hello);
    }
}
