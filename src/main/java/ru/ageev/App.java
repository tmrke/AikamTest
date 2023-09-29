package ru.ageev;

import ru.ageev.criteria.Criteria;
import ru.ageev.json_parser.ToCriteriaParser;
import ru.ageev.models.Customer;
import ru.ageev.searcher.Searcher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        String uri = "src/main/resources/criteria/criteria1.json";

        ToCriteriaParser jsonParser = new ToCriteriaParser();

        List<Criteria> criteria = jsonParser.getCriteriaList(uri);

        Searcher searcher = new Searcher(criteria);
        List<List<Customer>> customersByCriteria = searcher.getCustomersByCriteria();

        for (List<Customer> criteriaCustomers : customersByCriteria) {
            for (Customer customer : criteriaCustomers) {
                System.out.println("ID: " + customer.getId());
                System.out.println("Имя: " + customer.getName());
                System.out.println("Фамилия: " + customer.getLastname());
                System.out.println();
            }
        }
    }
}



