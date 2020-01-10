package com.guonl.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

/**
 * Created by guonl
 * Date: 2019-12-10 14:10
 * Description:
 */
@Service
public class TestThreadPoolService {

//    @Autowired
//    private ThreadPoolTaskExecutor executor;

    @Async
    public CompletableFuture<String> testAsync(){
        try {
//            System.out.println("当前活动线程数："+ executor.getActiveCount());
//            System.out.println("核心线程数："+ executor.getCorePoolSize());
//            System.out.println("总线程数："+ executor.getPoolSize());
//            System.out.println("最大线程池数量"+executor.getMaxPoolSize());
//            System.out.println("线程处理队列长度"+executor.getThreadPoolExecutor().getQueue().size());
//            System.out.println("---------------------------------");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("子任务异步线程：" + Thread.currentThread().getName());
    }


}
