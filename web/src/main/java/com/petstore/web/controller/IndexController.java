package com.petstore.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {

    @RequestMapping("")
    @ResponseBody
    public String ip() throws UnknownHostException {
        return Inet4Address.getLocalHost().getHostAddress();
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @RequestMapping("/world")
    @ResponseBody
    public String word() {
        return "world";
    }

    @RequestMapping("/status")
    @ResponseBody
    public String status() {
        return "SUCCESS";
    }
}
