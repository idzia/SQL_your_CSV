package com.codecool.view;

import com.codecool.model.Book;

import java.util.List;
import java.util.Scanner;

public class BasickView {
    public void mainView(List<String> menuList){
        System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        Integer number = 1;
        for (String item : menuList) {
            System.out.println(item + " press -> " + number);
            number++;
        }
        System.out.println("exit press -> 0");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }

    public int getInt(String text) {
        System.out.println(text);
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public String getString(String text) {
        System.out.println(text);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public void displayBookList(List<Book> bookList){
        for (Book book : bookList) {
            System.out.println(book.toString());
        }
    }

    public void displayBook(Book book){
        System.out.println(book.toString());

    }
}
