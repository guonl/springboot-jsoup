package com.guonl.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author guonl
 **/
@Slf4j
@Configuration
@EnableAsync
public class ThreadPoolConfig {
    // 核心线程池大小
    private int corePoolSize = 50;

    // 最大可创建的线程数
    private int maxPoolSize = 200;

    // 队列最大长度
    private int queueCapacity = 200;

    // 线程池维护线程所允许的空闲时间
    private int keepAliveSeconds = 300;

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("coin-");
        // 线程池对拒绝任务(无线程可用)的处理策略
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setRejectedExecutionHandler(customRejectedExecution());
        return executor;
    }


    /**
     * 自定义拒绝策略，默认是AbortPolicy，直接抛出RejectedExecutionException异常
     * https://www.jianshu.com/p/3cfd943996a1
     * @return
     */
    private RejectedExecutionHandler customRejectedExecution(){
        RejectedExecutionHandler handler = (r,executor)->{
            if (!executor.isShutdown()) {
                try {
                    log.info("start get queue");
                    executor.getQueue().put(r);
                    log.info("end get queue");
                } catch (InterruptedException e) {
                    log.error(e.toString(), e);
                    Thread.currentThread().interrupt();
                }
            }
        };
        return handler;
    }

}
