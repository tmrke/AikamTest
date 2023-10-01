package ru.ageev.json_convertor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ageev.service.Type;
import ru.ageev.criteria.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ReaderCriteria {
    public ReaderCriteria() {
    }

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

        return null; //TODO ERROR
    }

    private List<Criteria> getCriteriaByStat(String uri) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(new File(uri));
        StatisticCriteria criteria = (StatisticCriteria) objectMapper.treeToValue(root, getCriteriaClass(root));

        return Collections.singletonList(criteria);
    }

    private List<Criteria> getCriteriaBySearch(String uri) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode nodeArray = objectMapper.readTree(new File(uri)).get("criterias");
        List<Criteria> criteriaList = new ArrayList<>();

        if (nodeArray != null && nodeArray.isArray()) {
            for (JsonNode jsonNode : nodeArray) {
                criteriaList.add((Criteria) objectMapper.treeToValue(jsonNode, getCriteriaClass(jsonNode)));
            }
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
