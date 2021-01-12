package com.gmail.yauhen2012.service;

import java.io.IOException;

import com.gmail.yauhen2012.service.model.CrawlerDTO;

public interface CrawlerService {

    Boolean crawlerRun(CrawlerDTO crawlerDTO) throws IOException;

}
