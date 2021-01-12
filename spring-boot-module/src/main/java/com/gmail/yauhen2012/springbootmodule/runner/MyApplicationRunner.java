package com.gmail.yauhen2012.springbootmodule.runner;

import com.gmail.yauhen2012.service.CrawlerService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private final CrawlerService crawlerService;

    public MyApplicationRunner(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

}
