package com.codecool.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CsvReader implements Reader{

    private static final String DELIMETER= ",";

    public List<List<String>> read(String fileName) {

        List<List<String>> csvStringList = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            csvStringList = stream
                    .map(line -> Arrays.asList(line.split(DELIMETER)))
                    .collect(Collectors.toList());

        } catch (IOException e) {
           e.printStackTrace();
        }

        return csvStringList;

    }

}
