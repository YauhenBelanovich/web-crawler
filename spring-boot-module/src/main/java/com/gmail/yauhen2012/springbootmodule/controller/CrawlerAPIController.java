package com.gmail.yauhen2012.springbootmodule.controller;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import com.gmail.yauhen2012.service.CrawlerService;
import com.gmail.yauhen2012.service.model.CrawlerDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crawler-run")
public class CrawlerAPIController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final CrawlerService crawlerService;

    public CrawlerAPIController(CrawlerService crawlerService) {this.crawlerService = crawlerService;}

    @PostMapping
    public String crawlerRun(@RequestBody CrawlerDTO crawlerDTO) throws IOException {

        logger.debug("POST API crawler run method");
        if (crawlerService.crawlerRun(crawlerDTO)) {
            return "Started Successfully";
        } else {
            return "Run failed";
        }

    }

}
