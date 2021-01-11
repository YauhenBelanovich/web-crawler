package com.gmail.yauhen2012.springbootmodule.controller;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.gmail.yauhen2012.service.CrawlerService;
import com.gmail.yauhen2012.service.model.CrawlerDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(path = "/download")
    public ResponseEntity<Resource> download() throws IOException {

        File file = new File("C:\\Users\\Administrator\\projects\\web-crawler\\result.csv");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
