package org.example.service;

import org.example.common.Hello;

public class HelloService implements IHelloService {
    @Override
    public String sayHello(Hello hello) {
        System.out.println("你好，我收到的hello信息如下：");
        System.out.println("name" + hello.getName());
        System.out.println("description" + hello.getDescription());
        return "我们已经接收到该hello类，信息打印在控制台";
    }
}
