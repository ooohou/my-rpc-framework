package org.example.serviceRegistry;

import javafx.beans.binding.ObjectExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultServiceRegistry implements IServiceRegistry {

    private static final Logger logger = LoggerFactory.getLogger(DefaultServiceRegistry.class);
    //存储服务，需要同步,key是接口，value是实现类
    private Map<String, Object> serviceMap = new ConcurrentHashMap<>();
    //该方法创建一个并发的set,存的都是实现类
    private Set<Object> registeredService = ConcurrentHashMap.newKeySet();


    @Override
    public <T> void register(T service) {
        //该方法返回java定义的名称
        String serviceName = service.getClass().getCanonicalName();
        if (registeredService.contains(serviceName)){
            return;
        }
        registeredService.add(serviceName);
        Class[] interfaces = service.getClass().getInterfaces();
        if (interfaces.length == 0){
            return;
        }
        for (Class i : interfaces){
            //有一个问题，如果一个接口被多个类实现怎么办
            serviceMap.put(i.getCanonicalName(), service);
        }
        logger.info("Add service: {} and interfaces:{}", serviceName, service.getClass().getInterfaces());
    }

    @Override
    public Object getService(String serverName) {
        Object service = serviceMap.get(serverName);
        if (service == null){
            return null;
        }
        return service;
    }
}
