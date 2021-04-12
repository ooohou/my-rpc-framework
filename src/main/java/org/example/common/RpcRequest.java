package org.example.common;

import java.io.Serializable;

//用于发送的rpc请求
public class RpcRequest implements Serializable {
    //接口名
    String Interface;
    //方法名
    String MethodName;
    //传入参数
    Object[] parameters;
    public RpcRequest(){}
    public RpcRequest builder(){
        RpcRequest rpcRequest = new RpcRequest();
        return rpcRequest;
    }
    public RpcRequest setInterface(String Interface){
        this.Interface = Interface;
        return this;
    }
    public RpcRequest setMethodName(String MethodName){
        this.MethodName = MethodName;
        return this;
    }
    public RpcRequest setParameters(Object[] parameters){
        this.parameters = parameters;
        return this;
    }
    public RpcRequest build(){
        return new RpcRequest(this);
    }
    private RpcRequest(RpcRequest rpcRequest){
        this.parameters = rpcRequest.parameters;
        this.MethodName = rpcRequest.MethodName;
        this.Interface = rpcRequest.Interface;
    }
}
