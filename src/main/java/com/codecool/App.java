package com.codecool;


import com.codecool.dao.DataDao;
import com.codecool.dao.CsvReader;
import com.codecool.dao.DataDaoStream;
import com.codecool.dao.Reader;
import com.codecool.parser.Parser;
import com.codecool.view.BasicView;
import com.codecool.view.View;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String args[]) {
        System.out.println("file: book.csv");
        System.out.println("Enter your query: ");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoConfig.class);
        Reader data = context.getBean(CsvReader.class);
        View view = context.getBean(BasicView.class);


        Scanner input = new Scanner(System.in);
        Parser parser = new Parser(input.nextLine());

        String fileName = parser.getFileName();


        DataDao dataDaoStream = new DataDaoStream(data, fileName);

        List<String> condition = new ArrayList<String>();
        view.displayResult(dataDaoStream.select(fileName, parser.getQueryFields(), condition));

    }

}
