package com.codecool;

import com.codecool.controller.BookController;
import com.codecool.dao.BookDao;
import com.codecool.dao.BookDaoStreamImp;
import com.codecool.dao.CsvReader;
import com.codecool.model.Book;
import com.codecool.view.BasickView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        CsvReader streamCsv = new CsvReader();
        BookDao bookDaoStream = new BookDaoStreamImp(streamCsv.read());
        BookController bookController = new BookController(bookDaoStream);
        BasickView view = new BasickView();

        String[] menu = new String[] {
                                        "Select All",
                                        "Select By Title",
                                        "Select By Author"
                                    };

        List<String> menuList = Arrays.asList(menu);

//        Integer option = 1;
//        while (option != 0) {
//            view.mainView(menuList);
//            option = view.getInt("\nPress option: ");
//
//            switch (option) {
//                case 1:
//                    view.displayBookList(bookController.selectAllBook());
//                    break;
//                case 2:
//                    String title = view.getString("Enter lokking title: ");
//                    view.displayBook(bookController.selectBookByTitle(title));
//                    break;
//                case 3:
//                    String author = view.getString("Enter lokking author: ");
//                    view.displayBookList(bookController.selectBookByAuthor(author));
//                    break;
//                case 0:
//                    break;
//                default:
//                    System.out.println("There is no such option");
//                    break;
//            }



        }




//        List<Book> booksList = bookDaoStream.getAllBook();
//        for (Book book : booksList) {
//            System.out.println(book.toString());
//        }
//        Map<String, Integer> booksHeaders = bookDaoStream.getHeaders();
//        System.out.println(booksHeaders);
//
//        System.out.println("-------------------------------------");
//
//        Book book = ((BookDaoStreamImp) bookDaoStream).getBookById("5");
//        System.out.println(book.toString());
//
//        Book book2 = ((BookDaoStreamImp) bookDaoStream).getBookByTitle("The Simple Guide To Minimalist Life");
//        System.out.println(book2.toString());
//
//        List<Book> booksListbyAuthor = bookDaoStream.getBookByAuthor("Andrzej Bubrowiecki");
//        System.out.println(booksListbyAuthor.toString());
    }
//}
