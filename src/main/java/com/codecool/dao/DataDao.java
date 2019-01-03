package com.codecool.dao;

import java.util.List;
import java.util.Map;

public interface DataDao {

    Map<String, Integer> getFieldsNameMap(String fileName);

    List<List<String>> select(String fileName, List<String> queryFieldsList, List<String> conditionList);
}
