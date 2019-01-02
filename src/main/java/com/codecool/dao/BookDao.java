package com.codecool.dao;

import java.util.List;
import java.util.Map;

public interface BookDao {

    Map<String, Integer> getHeaders();
    List<String> getByFields(List<String> fieldsList);
    void getByCondition(List<String> whereConditionList);

//    List<Book> getAllBook();
//    Book getBookById(String id);
//    Book getBookByTitle(String title);
//    List<Book> getBookByAuthor(String author);
//    List<String> getBy(String condition);
}
