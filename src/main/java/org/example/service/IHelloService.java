package org.example.service;

import org.example.common.Hello;

public interface IHelloService {
    //给该服务定义一个方法,需要传入一个Hello类
    String sayHello(Hello hello);
}
