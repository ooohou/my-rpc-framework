package org.example.server;

import org.example.core.Server;
import org.example.service.Impl.HelloServiceImpl;
import org.example.service.IHelloService;
import org.example.serviceRegistry.DefaultServiceRegistry;
import org.example.serviceRegistry.IServiceRegistry;


public class ServerMain {
    public static void main(String[] args) {
        IHelloService helloService = new HelloServiceImpl();
        IServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        //手动注册服务
        serviceRegistry.register(helloService);
        Server server = new Server(serviceRegistry);
        server.start(8888);
    }
}
