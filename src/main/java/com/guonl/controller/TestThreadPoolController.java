package com.guonl.controller;

import com.guonl.service.TestThreadPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by guonl
 * Date: 2019-12-10 14:10
 * Description:
 */
@Controller
public class TestThreadPoolController {

    @Autowired
    private TestThreadPoolService testThreadPoolService;

    @RequestMapping("/test/thread")
    public ResponseEntity test() throws ExecutionException, InterruptedException {
        List<CompletableFuture<String>> completableList = new ArrayList<>();//保存异步返回的结果
        long start = System.currentTimeMillis();
        for (int i = 0; i < 808; i++) {
            CompletableFuture<String> future = testThreadPoolService.testAsync();
            completableList.add(future);

        }
        // Wait until they are all done
        //join() 的作用：让“主线程”等待“子线程”结束之后才能继续运行
        CompletableFuture<String>[] arr = new CompletableFuture[completableList.size()];
        completableList.toArray(arr);
        CompletableFuture.allOf(arr).join();
        for (CompletableFuture<String> future : completableList) {
            System.out.println(future.get());
        }
        long end = System.currentTimeMillis();

        return ResponseEntity.ok("本次任务耗时：" + (end - start));
    }


}
