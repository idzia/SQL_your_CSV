package com.codecool.dao;

import java.util.*;
import java.util.stream.Collectors;


public class DataDaoStream implements DataDao {

    //private List<List<String>> csvStringList;
    private Map<String, Integer> fieldsNameMap;
    private Reader fileData;


    public static final Integer HEADERS_INDEX  = 0;


//    public DataDaoStream(List<List<String>> csvStringList) {
//
//        this.csvStringList = csvStringList;
//        this.fieldsNameMap = getFieldsNameMap();
//    }

    public DataDaoStream(Reader fileData, String fileName) {
        this.fileData = fileData;
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

//    public Map<String, Integer> getFieldsNameMap() {
//        List<String> fieldsNameList = getFieldsNameList();
//        Map<String, Integer> fieldsNameMap = new HashMap<>();
//
//        for(String item : fieldsNameList) {
//            fieldsNameMap.put(item.toLowerCase(), fieldsNameList.indexOf(item));
//        }
//
//        return fieldsNameMap;
//    }
//
//    private List<String> getFieldsNameList() {
//        List<String> fieldsNameList = csvStringList.get(HEADERS_INDEX).stream()
//                .map(item -> item.toLowerCase())
//                .collect(Collectors.toList());
//
//        return fieldsNameList;
//    }

    private List<String> getFieldsNameList(String fileName) {
        List<String> fieldsNameList = fileData.read(fileName).get(HEADERS_INDEX).stream()
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

        List<List<String>> selectedColumnContent = getSelectedColumn(fileName, fieldsToSelectList);

        return selectedColumnContent;
    }

    private List<List<String>> getSelectedColumn(String fileName, List<String> fieldsToSelect) {
        List<List<String>> selectedColumnContent = fileData.read(fileName).stream()
                .skip(1)
                .map(item -> {
                    return fieldsToSelect.stream()
                            .map(field -> item.get(fieldsNameMap.get(field))).collect(Collectors.toList());
                }).collect(Collectors.toList());
        return selectedColumnContent;
    }


//    private List<List<String>> getSelectedRow(String fileName, List<String> fieldsToSelect, List<String> conditionList) {
//        List<List<String>> selectedColumnContent = getSelectedColumn(fileName, fieldsToSelect);

//        return selectedRowContent;
//    }


}
