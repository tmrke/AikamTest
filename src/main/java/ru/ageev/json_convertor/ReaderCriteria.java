package ru.ageev.json_convertor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ageev.service.Type;
import ru.ageev.criteria.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ReaderCriteria {
    public List<Criteria> getCriteriaList(String fileName, Type type) throws IOException {
        String uri = "src/main/resources/criteria/" + fileName;


        switch (type) {
            case search -> {
                return getCriteriaBySearch(uri);
            }
            case stat -> {
                return getCriteriaByStat(uri);
            }
        }

        return Collections.emptyList();
    }

    private List<Criteria> getCriteriaByStat(String uri) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Criteria> criteria = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(new File(uri));
            criteria.add((StatisticCriteria) objectMapper.treeToValue(root, getCriteriaClass(root)));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }

        return criteria;
    }

    private List<Criteria> getCriteriaBySearch(String uri) {
        List<Criteria> criteriaList = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode nodeArray = objectMapper.readTree(new File(uri)).get("criterias");

            if (nodeArray != null && nodeArray.isArray()) {
                for (JsonNode jsonNode : nodeArray) {
                    criteriaList.add((Criteria) objectMapper.treeToValue(jsonNode, getCriteriaClass(jsonNode)));
                }
            }
        } catch (IOException e) {
            criteriaList = Collections.emptyList();
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
                default -> ErrorCriteria.class;
            };
        }

        return ErrorCriteria.class;
    }
}
