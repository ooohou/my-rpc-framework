package org.example.core;

import org.example.dto.RpcRequest;
import org.example.dto.RpcResponse;
import org.example.enumeration.RpcErrorMessageEnum;
import org.example.enumeration.RpcResponseCodeEnum;
import org.example.exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    public Object sendRpcRequest(RpcRequest rpcRequest, String host, int port){
        try(Socket socket = new Socket(host, port)) {
            //获取socket的输出流，用一个对象输出流包装，专门用于序列化
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            //同理，通过输入流获取返回的参数
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcResponse rpcResponse = (RpcResponse) objectInputStream.readObject();
            if (rpcResponse == null){
                logger.error("方法调用失败，servername{}", rpcRequest.getMethodName());
                throw new RpcException(RpcErrorMessageEnum.SERVICE_INVOCATION_FAILURE, "interfaceName:" + rpcRequest.getInterfaceName());
            }
            if (rpcResponse.getCode() == null || !rpcResponse.getCode().equals(RpcResponseCodeEnum.SUCCESS.getCode())){
                logger.error("方法调用失败，servername{}, RpcResponse:{}", rpcRequest.getInterfaceName(), rpcResponse);
                throw new RpcException(RpcErrorMessageEnum.SERVICE_INVOCATION_FAILURE,"interfaceName:" + rpcRequest.getInterfaceName());
            }
            return rpcResponse;
        } catch (IOException | ClassNotFoundException e) {
            throw new RpcException("调用服务失败", e);
        }
    }
}
