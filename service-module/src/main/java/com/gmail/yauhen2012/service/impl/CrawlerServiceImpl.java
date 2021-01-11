package com.gmail.yauhen2012.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.gmail.yauhen2012.service.CrawlerService;
import com.gmail.yauhen2012.service.model.CrawlerDTO;
import com.gmail.yauhen2012.service.util.HTMLParserUtil;
import com.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
public class CrawlerServiceImpl implements CrawlerService {

    private final HTMLParserUtil htmlParserUtil;

    public CrawlerServiceImpl(HTMLParserUtil htmlParserUtil) {this.htmlParserUtil = htmlParserUtil;}

    @Override
    public Set<String> seleniumParse(String link, String text, WebDriver driver) throws IOException {
        driver.manage().window();
        System.out.println(link);
        driver.get(link);
        List<WebElement> links = driver.findElements(By.tagName("a"));
        List<Integer> count = extractWords(text)
                .stream()
                .map(word->driver
                        .findElements(By.xpath("//*[contains(text(),'" + word + "')]"))
                        .size())
                .collect(Collectors.toList());
        Integer sum = count.stream().reduce(0, Integer::sum);
        count.add(sum);
        String data = count.stream().map(Object::toString).collect(Collectors.joining(" "));

        Set<String> uniqueLinks = links.stream().map(e->e.getAttribute("href")).filter(Objects::nonNull).filter(e->e.startsWith("http")).collect(Collectors.toSet());

        writeToFile(link, data);
        writeCSV(link, data);

        return uniqueLinks;
    }

    @Override
    public Boolean crawlerRun(CrawlerDTO crawlerDTO) throws IOException {
        System.setProperty("webdriver.chrome.driver", "service-module/src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        Set<String> firstLoop = new HashSet<>(seleniumParse(crawlerDTO.getSeed(), crawlerDTO.getTerms(), driver));

        if (!firstLoop.isEmpty()) {

            Set<String> tempSet = new HashSet<>(firstLoop);

            System.out.println("===============" + firstLoop.size() + "==============START FIRST==================================");
            for (String s : firstLoop) {
                tempSet.addAll(seleniumParse(String.valueOf(s), crawlerDTO.getTerms(), driver));
            }
            System.out.println("=============================FINISH FIRST==================================");

            Set<String> secondLoop = new HashSet<>(tempSet);

            System.out.println("===============" + secondLoop.size() + "==============START SECOND==================================");
            for (String s : secondLoop) {
                if (!firstLoop.contains(s)) {
                    tempSet.addAll(seleniumParse(String.valueOf(s), crawlerDTO.getTerms(), driver));
                }
            }
            System.out.println("================================FINISH SECOND====================================");

            firstLoop = new HashSet<>(tempSet);
            for (String s : firstLoop) {
                if (!secondLoop.contains(s)) {
                    tempSet.addAll(seleniumParse(String.valueOf(s), crawlerDTO.getTerms(), driver));
                }
            }
            System.out.println("=================================FINISH FIRST=======================================");
            secondLoop = new HashSet<>(tempSet);
            for (String s : secondLoop) {
                if (!firstLoop.contains(s)) {
                    tempSet.addAll(seleniumParse(String.valueOf(s), crawlerDTO.getTerms(), driver));
                }
            }
            System.out.println("================================FINISH SECOND====================================");
        }
        driver.quit();
        return null;
    }

    private void writeToFile(String link, String data) {
        try(FileWriter writer = new FileWriter("result.txt", true))
        {
            writer.write(link + " " + data);
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void writeCSV(String link, String data) throws IOException {
        String csv = "result.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        String [] record = (link + "," + data).split(",");
        writer.writeNext(record);
        writer.close();

    }

    private Set<String> extractWords(String terms) {
        return Stream.of(terms.split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }

}
