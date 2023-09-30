package ru.ageev.json_parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ageev.Type;
import ru.ageev.criteria.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ReaderCriteria {
    public ReaderCriteria() {
    }

    public List<Criteria> getCriteriaList(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        String uri = "src/main/resources/criteria/" + fileName;

        File file = new File(uri);

        List<Criteria> criteriaList = new ArrayList<>();

        try {
            JsonNode nodeArray = objectMapper.readTree(file).get("criterias");  //TODO выбрать в зависимости от Типа

            if (nodeArray != null && nodeArray.isArray()) {
                for (JsonNode jsonNode : nodeArray) {
                    criteriaList.add((Criteria) objectMapper.treeToValue(jsonNode, getCriteriaClass(jsonNode)));
                }
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }

        return criteriaList;
    }

    private Class<?> getCriteriaClass(JsonNode jsonNode) {
        Iterator<String> fieldNames = jsonNode.fieldNames();

        while (fieldNames.hasNext()) {
            String str = fieldNames.next();

            return switch (str) {
                case "lastName" -> LastNameCriteria.class;
                case "productName" -> ProductNameAndCountCriteria.class;
                case "minExpenses" -> MinAndMaxExpensesCriteria.class;
                case "badCustomers" -> BadCustomersCountCriteria.class;
                case "startDate" -> StatisticCriteria.class;
                default -> throw new IllegalArgumentException("Неправильный формат критериев");
            };
        }

        throw new IllegalArgumentException("Неправильный формат критериев");
    }
}
