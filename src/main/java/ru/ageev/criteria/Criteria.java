package ru.ageev.criteria;

import ru.ageev.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Criteria {
    List<Customer> getCustomersByCriteria(Connection connection, Criteria criteria) throws SQLException;

    default List<Customer> getCustomersByPrepareStatement(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Customer> customers = new ArrayList<>();

        while (resultSet.next()) {
            Customer customer = new Customer();

            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("name"));
            customer.setLastname(resultSet.getString("last_name"));
            customers.add(customer);
        }

        return customers;
    }
}
