package ru.ageev;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ageev.criteria.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JsonParser {
    private ObjectMapper objectMapper;
    private List<Criteria> criteriaList;

    private File file;

    public JsonParser() {
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

    private Class getCriteriaType(JsonNode jsonNode) {
        Iterator<String> fieldNames = jsonNode.fieldNames();

        while (fieldNames.hasNext()) {
            String str = fieldNames.next();

            if (str.equals("lastName")) {
                return LastNameCriteria.class;
            } else if (str.contains("productName")) {
                return ProductNameAndCountCriteria.class;
            } else if (str.contains("minExpenses")) {
                return MinAndMaxExpenses.class;
            } else if (str.contains("badCustomers")) {
                return BadCustomersCount.class;
            } else {
                throw new IllegalArgumentException("Неправильный формат критериев");
                //TODO
            }
        }

        throw new IllegalArgumentException("Неправильный формат критериев");
    }
}
