package ru.ageev.json_parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ageev.criteria.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToCriteriaParser {
    private ObjectMapper objectMapper;

    private File file;

    public ToCriteriaParser() {
    }


    public List<Criteria> getCriteriaList(String uri) {
        objectMapper = new ObjectMapper();
        file = new File(uri);
        List<Criteria> criteriaList = new ArrayList<>();

        try {
            JsonNode nodeArray = objectMapper.readTree(file).get("criterias");

            if (nodeArray != null && nodeArray.isArray()) {
                for (JsonNode jsonNode : nodeArray) {
                    criteriaList.add((Criteria) objectMapper.treeToValue(jsonNode, getCriteriaType(jsonNode)));
                }
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }

        return criteriaList;
    }

    //TODO сделать список строк для сравнения в отдельном файле

    private Class getCriteriaType(JsonNode jsonNode) {
        Iterator<String> fieldNames = jsonNode.fieldNames();

        while (fieldNames.hasNext()) {
            String str = fieldNames.next();

            return switch (str) {
                case "lastName" -> LastNameCriteria.class;
                case "productName" -> ProductNameAndCountCriteria.class;
                case "minExpenses" -> MinAndMaxExpenses.class;
                case "badCustomers" -> BadCustomersCount.class;
                default -> throw new IllegalArgumentException("Неправильный формат критериев");

                //TODO
            };
        }

        throw new IllegalArgumentException("Неправильный формат критериев");
    }
}
