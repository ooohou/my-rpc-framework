package org.example.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//server类包含服务器的属性和方法
public class Server {
    //采用比较多的方法
    private ExecutorService ThreadPool;
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public Server(){
        //线程池参数
        int corePoolSize = 10;
        int maximumPoolSize = 100;
        long keepAliveTime = 1;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        this.ThreadPool = new ThreadPoolExecutor(corePoolSize,maximumPoolSize, keepAliveTime, TimeUnit.MINUTES, workQueue, threadFactory);
        //这也是一种新建线程池的方法
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
    }

    public void register(Object service, int port){
        try(ServerSocket serverSocket = new ServerSocket(port);) {
            logger.info("服务已经启动...");
            //serverSocket接受的是连接过来的套接字
            Socket accept;
            //通过while监视客户端
            while((accept = serverSocket.accept()) != null){
                logger.info("客户端已经连接");
                ThreadPool.execute(new workThread(accept, service));
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}
