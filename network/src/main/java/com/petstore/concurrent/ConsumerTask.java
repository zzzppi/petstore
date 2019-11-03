package com.petstore.concurrent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerTask.class);

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            logger.info("runner start" + Thread.currentThread().getName());
        } catch (Exception e) {
            logger.error("error", e);
        }
    }
}
