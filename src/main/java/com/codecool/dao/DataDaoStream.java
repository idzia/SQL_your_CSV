package com.codecool.dao;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class DataDaoStream implements DataDao {

    private Map<String, Integer> fieldsNameMap;
    private Reader reader;

    public static final Integer HEADERS_INDEX  = 0;

    public DataDaoStream(Reader reader, String fileName) {
        this.reader = reader;
        this.fieldsNameMap = getFieldsNameMap(fileName);
    }

    public Map<String, Integer> getFieldsNameMap(String fileName) {
        List<String> fieldsNameList = getFieldsNameList(fileName);
        Map<String, Integer> fieldsNameMap = new HashMap<>();

        for(String item : fieldsNameList) {
            fieldsNameMap.put(item.toLowerCase(), fieldsNameList.indexOf(item));
        }

        return fieldsNameMap;
    }

    private List<String> getFieldsNameList(String fileName) {
        List<String> fieldsNameList = reader.read(fileName).get(HEADERS_INDEX).stream()
                                        .map(item -> item.toLowerCase())
                                        .collect(Collectors.toList());

        return fieldsNameList;
    }

    public List<List<String>> select(String fileName, List<String> queryFieldsList, List<String> conditionList) {

        List<String> fieldsNameList = getFieldsNameList(fileName);
        List<String> fieldsToSelectList = new ArrayList<>();

        for (String field : queryFieldsList) {
            if (field.equals("*")) {
                fieldsToSelectList.addAll(fieldsNameList);
            } else fieldsToSelectList.add(field);
        }

        List<List<String>> selectedRowContent = getSelectedRow(fileName, conditionList);
        List<List<String>> selectedContent = getSelectedColumn(selectedRowContent, fieldsToSelectList);

        return selectedContent;
    }

    private List<List<String>> getSelectedColumn(List<List<String>> selectedRowContent, List<String> fieldsToSelect) {
        List<List<String>> selectedColumnContent = selectedRowContent.stream()
                .map(item -> {
                    return fieldsToSelect.stream()
                            .map(field -> item.get(fieldsNameMap.get(field))).collect(Collectors.toList());
                }).collect(Collectors.toList());
        return selectedColumnContent;
    }

    private List<List<String>> getSelectedRow(String fileName, List<String> validConditionList) {

        List<List<String>> selectedRowContent = reader.read(fileName).stream().skip(1)
                .filter(predicate(validConditionList))
                .collect(Collectors.toList());

        return selectedRowContent;
    }

    public Predicate<List<String>> predicate (List<String> validConditionList) {

        if (validConditionList.size()==0) {
            return item -> true;

        } else if (validConditionList.get(2).equals("=")) {
            return item -> item.get(fieldsNameMap.get(validConditionList.get(0))).equals(validConditionList.get(1));

        } else if (validConditionList.get(2).equals(">")) {
            return item ->
                    Integer.valueOf(item.get(fieldsNameMap.get(validConditionList.get(0)))) > Integer.valueOf(validConditionList.get(1));

        } else if (validConditionList.get(2).equals("<")) {
            return item ->
                    Integer.valueOf(item.get(fieldsNameMap.get(validConditionList.get(0)))) < Integer.valueOf(validConditionList.get(1));

        } else if (validConditionList.get(2).equals("<>")) {
            return item ->
                    Integer.valueOf(item.get(fieldsNameMap.get(validConditionList.get(0)))) != Integer.valueOf(validConditionList.get(1));

        } else if (validConditionList.get(2).equals("LIKE")) {
            return item -> {
                Pattern patern = Pattern.compile((validConditionList.get(1)).substring(1, (validConditionList.get(1).length())-1));
                return patern.matcher((item.get(fieldsNameMap.get(validConditionList.get(0)))).toLowerCase()).find();
            };

        }
            return item -> false;

    }

}
