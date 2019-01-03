package com.codecool;


import com.codecool.dao.CsvReader;
import com.codecool.dao.Reader;
import com.codecool.view.BasicView;
import com.codecool.view.View;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.codecool")


public class AutoConfig {

    @Bean(name="csvReader")
    public Reader createReader() {
        return new CsvReader();
    }

    @Bean(name="basicView")
    public View createView() {
        return new BasicView();
    }
}
