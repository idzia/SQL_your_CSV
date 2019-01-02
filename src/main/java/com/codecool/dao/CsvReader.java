package com.codecool.dao;

import com.codecool.model.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvReader {
//    private Map<String, String> columnsNameNumbersMap;
//    private List<Book> booksList;


    private static final String FILE_PATH = "book.csv";
    private static final String DELIMETER= ",";

    public List<List<String>> read() {

        List<List<String>> csvStringList = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(FILE_PATH))) {

            csvStringList = stream
                    .map(line -> Arrays.asList(line.split(DELIMETER)))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvStringList;

    }


    public List<List<String>> read(String tableName) {

        List<List<String>> csvStringList = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(tableName))) {

            csvStringList = stream
                    .map(line -> Arrays.asList(line.split(DELIMETER)))
                    .collect(Collectors.toList());

        } catch (IOException e) {
           e.printStackTrace();
        }

        return csvStringList;

    }

}
