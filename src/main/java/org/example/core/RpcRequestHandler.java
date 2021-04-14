package org.example.core;

import org.example.dto.RpcRequest;
import org.example.dto.RpcResponse;
import org.example.enumeration.RpcErrorMessageEnum;
import org.example.enumeration.RpcResponseCodeEnum;
import org.example.exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//该类主要用于处理请求
public class RpcRequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(RpcRequestHandler.class);

    //根据请求和对应服务调用方法
    public Object handle(RpcRequest rpcRequest, Object service){
        if (service == null){
            throw new RpcException(RpcErrorMessageEnum.SERVICE_CAN_NOT_BE_FOUND);
        }
        Object result = invokeTargetMethod(rpcRequest, service);
        logger.info("service:{} 成功调用方法: {}", rpcRequest.getInterfaceName(), rpcRequest.getMethodName());
        return result;

    }

    public Object invokeTargetMethod(RpcRequest rpcRequest, Object service){
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            if (method == null){
                return RpcResponse.fail(RpcResponseCodeEnum.METHOD_NOT_FOUND);
            }
            return method.invoke(service, rpcRequest.getParameters());
        } catch (NoSuchMethodException e) {
            logger.error("method not found:" + e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
