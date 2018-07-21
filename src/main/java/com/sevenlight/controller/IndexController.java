package com.sevenlight.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class IndexController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index")
    public String index() {
        log.info("这个是测试log");
        return "success";
    }
}
