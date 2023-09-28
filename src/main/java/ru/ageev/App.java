package ru.ageev;


import config.DatabaseConnection;
import ru.ageev.criteria.Criteria;
import ru.ageev.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        String uri = "src/main/resources/criteria/criteria1.json";

        JsonParser jsonParser = new JsonParser();

        List<Criteria> criteria = jsonParser.getCriteriaList(uri);
        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE last_name='Иванов'");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Customer> customers = new ArrayList<>();

        while (resultSet.next()){
            Customer customer = new Customer();

            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("name"));
            customer.setLastname(resultSet.getString("last_name"));

            customers.add(customer);
        }

        System.out.println(customers.get(0).getLastname());

        //List<Customer> customers = Collections.singletonList(preparedStatement.executeQuery().getObject("last_name", Customer.class));
    }
}



