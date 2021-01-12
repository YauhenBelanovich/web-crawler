package com.gmail.yauhen2012.service.impl;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.gmail.yauhen2012.service.CSVService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class CSVServiceImpl implements CSVService {

    @Override
    public Resource loadAsResource(String filename) throws FileNotFoundException {
        try {
            Path file = Paths.get(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException(
                        "Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + filename);
        }
    }

}
