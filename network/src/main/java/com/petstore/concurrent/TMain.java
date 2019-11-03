package com.petstore.concurrent;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class TMain {
    private static final Logger logger = LoggerFactory.getLogger(TMain.class);

    public static void main(String[] args) throws Exception {
        logger.info("main start");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, Runtime.getRuntime().availableProcessors() * 2 + 1,
                3000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
                new ThreadFactory() {
                    AtomicInteger idx = new AtomicInteger(0);

                    @Override
                    public Thread newThread(@NotNull Runnable r) {
                        return new Thread(r, "delta-dump-storage-detail-" + idx.getAndIncrement());
                    }
                });
        executor.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 10; i++) {
            executor.submit(new ConsumerTask());
        }
        System.in.read();
    }
}
