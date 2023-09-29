package ru.ageev;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ageev.criteria.Criteria;
import ru.ageev.dao.CustomerDao;
import ru.ageev.json_parser.ToCriteriaParser;
import ru.ageev.models.Customer;
import ru.ageev.searcher.Searcher;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        String uri = "src/main/resources/criteria/criteria1.json";

        ToCriteriaParser jsonParser = new ToCriteriaParser();
        List<Criteria> criteria = jsonParser.getCriteriaList(uri);

        Searcher searcher = new Searcher(criteria);
        LinkedHashMap<Criteria, List<CustomerDao>> customersListsByCriteria = searcher.getCustomersByCriteria();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File("output.json"), customersListsByCriteria);
            System.out.println("success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



