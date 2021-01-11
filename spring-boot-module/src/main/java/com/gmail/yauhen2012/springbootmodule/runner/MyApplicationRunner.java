package com.gmail.yauhen2012.springbootmodule.runner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.gmail.yauhen2012.service.CrawlerService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
//        System.setProperty("webdriver.chrome.driver", "service-module/src/main/resources/chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//
//        String startLink = "https://www.ukulele.by/sheet-music";
//        String searchTerms = "test, тест, это, тестирование";
//
//        Set<String> firstLoop = new HashSet<>(crawlerService.seleniumParse(startLink, searchTerms, driver));
//
//        if (!firstLoop.isEmpty()) {
//
//            Set<String> tempSet = new HashSet<>(firstLoop);
//
//            System.out.println("===============" + firstLoop.size() + "==============START FIRST==================================");
//            for (String s : firstLoop) {
//                tempSet.addAll(crawlerService.seleniumParse(String.valueOf(s), searchTerms, driver));
//            }
//            System.out.println("=============================FINISH FIRST==================================");
//
//            Set<String> secondLoop = new HashSet<>(tempSet);
//
//            System.out.println("===============" + secondLoop.size() + "==============START SECOND==================================");
//            for (String s : secondLoop) {
//                if (!firstLoop.contains(s)) {
//                    tempSet.addAll(crawlerService.seleniumParse(String.valueOf(s), searchTerms, driver));
//                }
//            }
//            System.out.println("================================FINISH SECOND====================================");
//
//            firstLoop = new HashSet<>(tempSet);
//            for (String s : firstLoop) {
//                if (!secondLoop.contains(s)) {
//                    tempSet.addAll(crawlerService.seleniumParse(String.valueOf(s), searchTerms, driver));
//                }
//            }
//            System.out.println("=================================FINISH FIRST=======================================");
//            secondLoop = new HashSet<>(tempSet);
//            for (String s : secondLoop) {
//                if (!firstLoop.contains(s)) {
//                    tempSet.addAll(crawlerService.seleniumParse(String.valueOf(s), searchTerms, driver));
//                }
//            }
//            System.out.println("================================FINISH SECOND====================================");
//        }
//
//        driver.quit();
    }

}
