package com.codecool;

import com.codecool.dao.BookDao;
import com.codecool.dao.BookDaoStreamImp;
import com.codecool.dao.CsvReader;
import com.codecool.parser.Parser;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class App {


    public static void main(String args[]) {
        System.out.println("file: book.csv");
        System.out.println("Enter your query: ");
        Scanner input = new Scanner(System.in);
        Parser parser = new Parser(input.nextLine());


        CsvReader streamCsv = new CsvReader();
        String fileName = parser.getFileName();
        BookDao bookDaoStream = new BookDaoStreamImp(streamCsv.read(fileName));

        List<String> whereConditionsList = parser.getWhereCondition();

        bookDaoStream.getByCondition(whereConditionsList);


    }

}
