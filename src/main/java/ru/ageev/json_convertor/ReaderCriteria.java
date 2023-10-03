package ru.ageev.json_convertor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import ru.ageev.exception.IncorrectDateException;
import ru.ageev.exception.IncorrectStartEndDateException;
import ru.ageev.service.DataValidator;
import ru.ageev.service.Type;
import ru.ageev.criteria.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ReaderCriteria {
    public List<Criteria> getCriteriaList(String fileName, Type type) throws IOException, IncorrectDateException, IncorrectStartEndDateException {
        String uri = "src/main/resources/criteria/" + fileName;

        if (type == Type.search) {
            return getCriteriaBySearch(uri);
        } else if (type == Type.stat) {
            return getCriteriaByStat(uri);
        }

        List<Criteria> error = new ArrayList<>();
        error.add(new ErrorCriteria());

        return error;
    }

    private List<Criteria> getCriteriaByStat(String uri) throws IOException, IncorrectStartEndDateException, IncorrectDateException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Criteria> criteria = new ArrayList<>();
        try {
            JsonNode root;
            StatisticCriteria statCriteria;

            try {
                root = objectMapper.readTree(new File(uri));
                statCriteria = (StatisticCriteria) objectMapper.treeToValue(root, getCriteriaClass(root));
            } catch (InvalidFormatException e) {
                criteria.add(new ErrorCriteria(e.getMessage()));
                throw new IncorrectDateException();
            }

            try {
                DataValidator.checkValid(statCriteria.getStartDate(), statCriteria.getEndDate());
            } catch (IncorrectStartEndDateException e) {
                criteria.add(new ErrorCriteria(e.getMessage()));
                throw new IncorrectStartEndDateException();
            } catch (IncorrectDateException e) {
                criteria.add(new ErrorCriteria(e.getMessage()));
                throw new IncorrectDateException();
            }

            criteria.add(statCriteria);
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

            switch (str) {
                case "lastName":
                    return LastNameCriteria.class;
                case "productName":
                    return ProductNameAndCountCriteria.class;
                case "minExpenses":
                    return MinAndMaxExpensesCriteria.class;
                case "badCustomers":
                    return BadCustomersCountCriteria.class;
                case "startDate":
                    return StatisticCriteria.class;
            }
        }

        return ErrorCriteria.class;
    }
}
