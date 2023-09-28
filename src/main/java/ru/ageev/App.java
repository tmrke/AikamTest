package ru.ageev;


import ru.ageev.criteria.Criteria;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String uri = "src/main/resources/criteria/criteria1.json";

        JsonParser jsonParser = new JsonParser();

        List<Criteria> criteria = jsonParser.getCriteriaList(uri);
    }
}


