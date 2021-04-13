package org.example.common;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

//用于发送的rpc请求
@Data
@Builder
public class RpcRequest implements Serializable {
    //接口名
    String interfaceName;
    //方法名
    String methodName;
    //传入参数
    Object[] parameters;
    Class<?>[] paramTypes;
    /*public RpcRequest(){}
    public RpcRequest builder(){
        RpcRequest rpcRequest = new RpcRequest();
        return rpcRequest;
    }
    public RpcRequest setInterface(String interfaceName){
        this.interfaceName = interfaceName;
        return this;
    }
    public RpcRequest setMethodName(String methodName){
        this.methodName = methodName;
        return this;
    }
    public RpcRequest setParameters(Object[] parameters){
        this.parameters = parameters;
        return this;
    }

    public RpcRequest setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
        return this;
    }

    public RpcRequest setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
        return this;
    }

    public RpcRequest build(){
        return new RpcRequest(this);
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    private RpcRequest(RpcRequest rpcRequest){
        this.parameters = rpcRequest.parameters;
        this.methodName = rpcRequest.methodName;
        this.interfaceName = rpcRequest.interfaceName;
        this.paramTypes = rpcRequest.paramTypes;
    }*/
}
