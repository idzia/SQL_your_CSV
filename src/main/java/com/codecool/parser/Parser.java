package com.codecool.parser;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {

    private List<String> parsedQueryList;
    private static final String DELIMETER = "SELECT|FROM|WHERE|select|from|where";
    private static final String OR_AND = "OR|AND|or|and";
    private static final String REGEX = "^SELECT\\s.+\\sFROM\\s.+\\.CSV$";
    private static final String REGEX_WHERE = "^SELECT\\s.+\\sFROM\\s.+\\.CSV\\sWHERE\\s.+$";

    private static final int FIELDS = 0;
    private static final int FILE_NAME = 1;
    private static final int WHERE_CONDITION = 2;


    public Parser(String query) {

        if (validate(query.trim().toUpperCase())) {
            this.parsedQueryList = Arrays.stream(query.split(DELIMETER))
                    .map(item -> item.trim())
                    .skip(1)
                    .collect(Collectors.toList());

        } else throw new IllegalArgumentException("Query is not valid");

    }

    public boolean validate(String query) {

        Pattern compileSelectFrom = Pattern.compile(REGEX);
        Pattern compileSelectFromWhere = Pattern.compile(REGEX_WHERE);

        if (compileSelectFrom.matcher(query).matches() || compileSelectFromWhere.matcher(query).matches()){
            return true;
        }
        return false;
    }


    public List<String> getQueryFields() {
        List<String> queryFieldsList = Arrays.stream(parsedQueryList.get(FIELDS).split(","))
                                        .map(item-> item.trim()).collect(Collectors.toList());
        return queryFieldsList;
    }

    public String getFileName() {
        return parsedQueryList.get(FILE_NAME);
    }

    public List<String> getWhereCondition() {
        List<String> whereConditionsList = Arrays.stream(parsedQueryList.get(WHERE_CONDITION).split(OR_AND))
                                            .map(item-> item.trim()).collect(Collectors.toList());
        return whereConditionsList;
    }
}
