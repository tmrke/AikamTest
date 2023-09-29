package ru.ageev;


import ru.ageev.criteria.Criteria;
import ru.ageev.json_parser.ToCriteriaParser;
import ru.ageev.models.Customer;
import ru.ageev.searcher.Searcher;

import java.sql.*;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        String uri = "src/main/resources/criteria/criteria1.json";

        ToCriteriaParser jsonParser = new ToCriteriaParser();

        List<Criteria> criteria = jsonParser.getCriteriaList(uri);
//        Connection connection = DatabaseConnection.getConnection();
//
//        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE last_name='Иванов'");
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        List<Customer> customers = new ArrayList<>();
//
//        while (resultSet.next()){
//            Customer customer = new Customer();
//
//            customer.setId(resultSet.getInt("id"));
//            customer.setName(resultSet.getString("name"));
//            customer.setLastname(resultSet.getString("last_name"));
//
//            customers.add(customer);
//        }
//
//        System.out.println(customers.get(0).getLastname());

        Searcher searcher = new Searcher(criteria);
        List<Customer> customersByCriteria = searcher.getCustomersByCriteria();

        System.out.println(customersByCriteria.get(0).getLastname());
    }
}



