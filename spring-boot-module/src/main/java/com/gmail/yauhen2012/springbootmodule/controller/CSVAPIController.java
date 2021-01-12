package com.gmail.yauhen2012.springbootmodule.controller;

import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;

import com.gmail.yauhen2012.service.CSVService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class CSVAPIController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final CSVService csvService;

    public CSVAPIController(CSVService csvService) {this.csvService = csvService;}

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws MalformedURLException, FileNotFoundException {

        Resource resource = csvService.loadAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
