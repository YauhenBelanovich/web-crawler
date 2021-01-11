package com.gmail.yauhen2012.service.util;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class HTMLParserUtil {

    public Document parseHtmlByLinkReturnDoc(String link) throws IOException {
        return Jsoup.connect(link).method(Connection.Method.GET).execute().parse();
    }

}
