package org.example.server;

import org.example.core.Server;
import org.example.service.HelloService;
import org.example.service.IHelloService;


public class ServerMain {
    public static void main(String[] args) {
        IHelloService helloService = new HelloService();
        Server server = new Server();
        server.register(helloService, 8888);
    }
}
