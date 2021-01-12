package com.gmail.yauhen2012.service.impl;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Set;

import com.gmail.yauhen2012.service.CrawlerService;
import com.gmail.yauhen2012.service.model.CrawlerDTO;
import com.gmail.yauhen2012.service.util.SeleniumParserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
public class CrawlerServiceImpl implements CrawlerService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final SeleniumParserUtil seleniumParserUtil;

    public CrawlerServiceImpl(SeleniumParserUtil seleniumParserUtil) {this.seleniumParserUtil = seleniumParserUtil;}

    @Override
    public Boolean crawlerRun(CrawlerDTO crawlerDTO) throws IOException {
        System.setProperty("webdriver.chrome.driver", "service-module/src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        Set<String> firstLoop = new HashSet<>(seleniumParserUtil.seleniumParse(crawlerDTO.getSeed(), crawlerDTO.getTerms(), driver));

        if (!firstLoop.isEmpty()) {

            Set<String> tempSet = new HashSet<>(firstLoop);

            logger.info("=============== Target - " + firstLoop.size() + "  ====START FIRST LOOP==================================");
            for (String s : firstLoop) {
                tempSet.addAll(seleniumParserUtil.seleniumParse(String.valueOf(s), crawlerDTO.getTerms(), driver));
            }
            logger.info("=============================FINISH FIRST==================================");

            Set<String> secondLoop = new HashSet<>(tempSet);

            logger.info("=============== Target - " + secondLoop.size() + "==============START SECOND LOOP==================================");
            for (String s : secondLoop) {
                if (!firstLoop.contains(s)) {
                    tempSet.addAll(seleniumParserUtil.seleniumParse(String.valueOf(s), crawlerDTO.getTerms(), driver));
                }
            }
            logger.info("================================FINISH SECOND====================================");

            firstLoop = new HashSet<>(tempSet);
            for (String s : firstLoop) {
                if (!secondLoop.contains(s)) {
                    tempSet.addAll(seleniumParserUtil.seleniumParse(String.valueOf(s), crawlerDTO.getTerms(), driver));
                }
            }

            secondLoop = new HashSet<>(tempSet);
            for (String s : secondLoop) {
                if (!firstLoop.contains(s)) {
                    tempSet.addAll(seleniumParserUtil.seleniumParse(String.valueOf(s), crawlerDTO.getTerms(), driver));
                }
            }

        }
        driver.quit();
        return null;
    }

}
