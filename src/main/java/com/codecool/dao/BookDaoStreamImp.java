package com.codecool.dao;

import com.codecool.model.Book;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookDaoStreamImp implements BookDao {

    private List<List<String>> csvStringList;
    private Map<String, Integer> headersNameMap;


    public static final Integer HEADERS_INDEX  = 0;


    public BookDaoStreamImp(List<List<String>> csvStringList) {

        this.csvStringList = csvStringList;
        this.headersNameMap = getHeaders();
    }

    public Map<String, Integer> getHeaders() {
        List<String> fileHeaders = csvStringList.get(HEADERS_INDEX);
        Map<String, Integer> headersMap = new HashMap<>();

        for(String item : fileHeaders) {
            headersMap.put(item.toLowerCase(), fileHeaders.indexOf(item));
        }

        return headersMap;
    }

    public List<String> getAllFields() {
        List<String> fileHeaders = csvStringList.get(HEADERS_INDEX).stream().map(item -> item.toLowerCase()).collect(Collectors.toList());

        return fileHeaders;
    }

    public List<String> getByFields(List<String> fieldsList) {
        List<String> allFields = getAllFields();
        List<String> newFieldsList = new ArrayList<>();

        for (String field : fieldsList) {
            if (field.equals("*")) {
                newFieldsList.addAll(allFields);
            } else newFieldsList.add(field);
        }

        List<String> resultList = csvStringList.stream()
                .skip(1)
                .map(item -> {
                    return newFieldsList.stream()
                            .map(field -> item.get(headersNameMap.get(field))).collect(Collectors.joining(" "));
                }).collect(Collectors.toList());

        return resultList;
    }

    public void getByCondition(List<String> whereConditionList){
        System.out.println(whereConditionList.size());
    }



//    public Predicate<String> id() {
//        return book -> book
//    }
//    public void select (List<Predicate<String>> rules, List<Book> bookList) {
//        bookList.stream().filter(rules.stream().reduce(Predicate::and).orElse(p->false)).forEach(System.out::println);
//    }


//    public List<Book> getAllBook() {
//        List<Book> booksList = csvStringList.stream()
//                .skip(1)
//                .map(item -> new Book(item.get(BOOK_ID), item.get(BOOK_AUTHOR), item.get(BOOK_TITLE)))
//                .collect(Collectors.toList());
//        return booksList;
//    }

//
//    public Map<String, Integer> getHeaders() {
//        List<String> fileHeaders = csvStringList.get(HEADERS_INDEX);
//        Map<String, Integer> headersMap = new HashMap<>();
//
//        for(String item : fileHeaders) {
//            headersMap.put(item.toLowerCase(), fileHeaders.indexOf(item));
//        }
//
//        return headersMap;
//    }
//
//    public Book getBookById(String id) {
//        Optional<Book> optionalFilteredBook = getAllBook().stream()
//                .filter(book -> book.getId().equals(id)).findFirst();
//
//        Book filteredBook = optionalFilteredBook.orElse(null);
//
//        return filteredBook;
//    }
//
//
//    public Book getBookByTitle(String title) {
//        Optional<Book> optionalFilteredBook = getAllBook().stream()
//                .filter(book -> book.getTitle().equalsIgnoreCase(title)).findFirst();
//
//        Book filteredBook = optionalFilteredBook.orElse(null);
//
//
//        return filteredBook;
//    }
//
//    public List<Book> getBookByAuthor(String author) {
//        List<Book> filteredBookList = getAllBook().stream()
//                .filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
//
//
//        return filteredBookList;
//    }

//    public List<String> getBy(String condition) {
//
//        Integer conditionIndex = headersNameMap.get(condition.toLowerCase());
//
//
//        List<String> filteredString =
//                csvStringList.stream()
//                        .skip(1)
//                        .map(book -> book.get(conditionIndex))
//                        .collect(Collectors.toList());
//
//        return filteredString;
//    }

}
