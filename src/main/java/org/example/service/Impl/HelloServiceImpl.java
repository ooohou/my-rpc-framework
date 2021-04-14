package org.example.service.Impl;

import org.example.pojo.Hello;
import org.example.service.IHelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceImpl implements IHelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    public String sayHello(Hello hello) {
        logger.info("你好，我收到的hello信息如下：");
        logger.info("name" + hello.getName());
        logger.info("description" + hello.getDescription());
        return "我们已经接收到该hello类，信息打印在控制台";
    }
}
