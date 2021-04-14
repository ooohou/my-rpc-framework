package org.example.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

import org.example.serviceRegistry.DefaultServiceRegistry;
import org.example.serviceRegistry.IServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.spi.ServiceRegistry;

//server类包含服务器的属性和方法
public class Server {
    //线程池参数
    private static final int CORE_POOL_SIZE = 10;
    private static final int MAXIMUM_POOL_SIZE = 100;
    private static final long KEEP_ALIVE_TIME = 1;
    private static final int BLOCKING_QUE_SIZE = 100;
    private ExecutorService threadPool;
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    private IServiceRegistry serviceRegistry;
    private RpcRequestHandler rpcRequestHandler = new RpcRequestHandler();

    public Server(IServiceRegistry serviceRegistry){
        this.serviceRegistry = serviceRegistry;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(BLOCKING_QUE_SIZE);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        this.threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES,workQueue, threadFactory);
    }

    public void start(int port){
        try(ServerSocket serverSocket = new ServerSocket(port);) {
            logger.info("服务已经启动...");
            //serverSocket接受的是连接过来的套接字
            Socket accept;
            //通过while监视客户端
            while((accept = serverSocket.accept()) != null){
                logger.info("客户端已经连接");
                threadPool.execute(new RpcRequestHandlerRunnable(accept, rpcRequestHandler, serviceRegistry));
            }
            threadPool.shutdown();
        } catch (IOException  e) {
            logger.error("occur IOException:", e);
        }
    }
}
