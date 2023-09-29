package ru.ageev.searcher;

import ru.ageev.config.DatabaseConnection;
import ru.ageev.criteria.*;
import ru.ageev.models.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Searcher {
    private final Connection connection = DatabaseConnection.getConnection();
    private List<Criteria> criteriaList;
    private List<Customer> customerList;

    public Searcher(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public List<Customer> getCustomersByCriteria() throws SQLException {
        String query;

        for (Criteria currentCriteria : criteriaList) {
            if (currentCriteria instanceof LastNameCriteria) {
                query = "SELECT * FROM customer WHERE last_name=?";
            } else if (currentCriteria instanceof ProductNameAndCountCriteria) {
                query = "SELECT * FROM customer WHERE last_name=?";
            } else if (currentCriteria instanceof MinAndMaxExpenses) {
                query = "SELECT * FROM customer WHERE last_name=?";
            } else if (currentCriteria instanceof BadCustomersCount) {
                query = "SELECT * FROM customer WHERE last_name=?";
            } else {
                throw new IllegalArgumentException("Не найден корректный критерий");
            }

            ResultSet resultSet = connection.prepareStatement(query).executeQuery();

            customerList = new ArrayList<>();
            Customer customer = new Customer();

            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("name"));
            customer.setLastname(resultSet.getString("last_name"));

            customerList.add(customer);
        }

        return customerList;
    }
}
