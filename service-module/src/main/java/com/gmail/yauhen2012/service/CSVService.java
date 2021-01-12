package com.gmail.yauhen2012.service;

import java.io.FileNotFoundException;

import org.springframework.core.io.Resource;

public interface CSVService {

    Resource loadAsResource(String filename) throws FileNotFoundException;
}
