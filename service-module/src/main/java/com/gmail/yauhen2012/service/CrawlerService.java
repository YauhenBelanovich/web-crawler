package com.gmail.yauhen2012.service;

import java.io.IOException;
import java.util.Set;

import com.gmail.yauhen2012.service.model.CrawlerDTO;
import org.openqa.selenium.WebDriver;

public interface CrawlerService {

    Set<String> seleniumParse(String link, String text, WebDriver driver) throws IOException;

    Boolean crawlerRun (CrawlerDTO crawlerDTO) throws IOException;

}
