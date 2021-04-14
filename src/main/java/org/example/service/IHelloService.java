package org.example.service;

import org.example.pojo.Hello;

public interface IHelloService {
    //给该服务定义一个方法,需要传入一个Hello类
    public String sayHello(Hello hello);
}
