package com.petstore.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Bootstrap {
    private static final Log log = LogFactory.getLog(Bootstrap.class);

    public static void main(String[] args) {
        System.out.println(log.getClass());
        if (log instanceof java.util.logging.Logger) {
            System.out.println("jul");
        }
        log.info("hello world");

    }
}
