package com.gmail.yauhen2012.service.util;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class SeleniumParserUtil {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public Set<String> seleniumParse(String link, String text, WebDriver driver) throws IOException {
        driver.manage().window();
        logger.info(link);
        driver.get(link);
        List<WebElement> links = driver.findElements(By.tagName("a"));
        List<Integer> count = extractWords(text)
                .stream()
                .map(word -> driver
                        .findElements(By.xpath("//*[contains(text(),'" + word + "')]"))
                        .size())
                .collect(Collectors.toList());
        Integer sum = count.stream().reduce(0, Integer::sum);
        count.add(sum);
        String data = count.stream().map(Object::toString).collect(Collectors.joining(" "));

        Set<String> uniqueLinks = links.stream()
                .map(e -> e.getAttribute("href"))
                .filter(Objects::nonNull)
                .filter(e -> e.startsWith("http"))
                .collect(Collectors.toSet());

        //writeToFile(link, data);
        writeCSV(link, data);

        return uniqueLinks;
    }

    private void writeCSV(String link, String data) throws IOException {
        String csv = "result.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        String[] record = (link + "," + data).split(",");
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
