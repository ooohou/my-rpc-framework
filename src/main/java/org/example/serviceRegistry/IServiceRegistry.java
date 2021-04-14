package org.example.serviceRegistry;

public interface IServiceRegistry {
    //泛型修饰方法名
    <T>void register(T service);
    //获取服务
    Object getService(String serviceName);
}
