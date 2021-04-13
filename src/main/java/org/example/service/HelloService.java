package org.example.service;

import org.example.common.Hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloService implements IHelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);
    @Override
    public String sayHello(Hello hello) {
        logger.info("你好，我收到的hello信息如下：");
        logger.info("name" + hello.getName());
        logger.info("description" + hello.getDescription());
        return "我们已经接收到该hello类，信息打印在控制台";
    }
}
