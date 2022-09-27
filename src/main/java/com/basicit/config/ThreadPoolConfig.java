package com.basicit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Crackers
 * @date 2021-12-02 17:33
 */
@Configuration
public class ThreadPoolConfig {


    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //Set the number of core threads
        taskExecutor.setCorePoolSize(2);
        //Set the maximum number of threads
        taskExecutor.setMaxPoolSize(10);
        //Set thread idle wait time
        taskExecutor.setKeepAliveSeconds(3000);
        //Set the size of the task waiting queue
        taskExecutor.setQueueCapacity(100);
        // Set the prefix of the thread name in the thread pool————Recommended by Ali Coding Protocol--It is convenient for debugging after errors
        taskExecutor.setThreadNamePrefix("site-tool-task-");
        //Set the rejection policy of the task, a variable of type RejectedExecutionHandler, which represents the saturation policy of the thread pool.
        //If the blocking queue is full and there are no idle threads, then if you continue to submit tasks, you need to adopt a strategy to handle the task. The thread pool provides 4 strategies:
        //AbortPolicy：Throwing an exception directly is the default strategy;
        //CallerRunsPolicy：Use the thread where the caller is located to execute the task;
        //DiscardOldestPolicy：Discard the top task in the blocking queue and execute the current task;
        //DiscardPolicy：Just drop the task；
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //initialization
        taskExecutor.initialize();
        return taskExecutor;
    }
}
