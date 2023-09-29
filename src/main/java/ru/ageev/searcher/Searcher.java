package ru.ageev.searcher;

import ru.ageev.config.DatabaseConnection;
import ru.ageev.criteria.*;
import ru.ageev.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Searcher {
    private final Connection connection = DatabaseConnection.getConnection();
    private List<Criteria> criteriaList;

    public Searcher(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public List<List<Customer>> getCustomersByCriteria() throws SQLException {
        String query;
        ResultSet resultSet;
        List<List<Customer>> allCustomersLists = new ArrayList<>();

        for (Criteria currentCriteria : criteriaList) {
            if (currentCriteria instanceof LastNameCriteria) {
                List<Customer> customersByLastNameCriteria = new ArrayList<>();
                query = "SELECT * FROM customers WHERE last_name=?";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, ((LastNameCriteria) currentCriteria).getLastName());
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Customer customer = new Customer();

                    customer.setId(resultSet.getInt("id"));
                    customer.setName(resultSet.getString("name"));
                    customer.setLastname(resultSet.getString("last_name"));
                    customersByLastNameCriteria.add(customer);
                }

                allCustomersLists.add(customersByLastNameCriteria);
            } else if (currentCriteria instanceof ProductNameAndCountCriteria) {
                List<Customer> customersByProductNameAndCountCriteria = new ArrayList<>();

                query = "SELECT c.* " +
                        "FROM customers c " +
                        "WHERE c.id IN ( " +
                        "    SELECT o.customer_id " +
                        "    FROM orders o " +
                        "    JOIN products p ON o.product_id = p.id " +
                        "    WHERE p.name = ? " +
                        "    GROUP BY o.customer_id " +
                        "    HAVING COUNT(*) >= ? " +
                        ");";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, ((ProductNameAndCountCriteria) currentCriteria).getProductName());
                preparedStatement.setInt(2, ((ProductNameAndCountCriteria) currentCriteria).getMinTimes());

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Customer customer = new Customer();

                    customer.setId(resultSet.getInt("id"));
                    customer.setName(resultSet.getString("name"));
                    customer.setLastname(resultSet.getString("last_name"));

                    customersByProductNameAndCountCriteria.add(customer);
                }

                allCustomersLists.add(customersByProductNameAndCountCriteria);
            } else if (currentCriteria instanceof MinAndMaxExpensesCriteria) {
                List<Customer> customersByMinAndMaxExpensesCriteria = new ArrayList<>();

                query = "SELECT c.* " +
                        "FROM customers c " +
                        "WHERE c.id IN ( " +
                        "    SELECT o.customer_id " +
                        "    FROM orders o " +
                        "    JOIN products p ON o.product_id = p.id " +
                        "    GROUP BY o.customer_id " +
                        "    HAVING SUM(p.price) BETWEEN ? AND ?" +
                        ");";

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setInt(1, ((MinAndMaxExpensesCriteria) currentCriteria).getMinExpenses());
                preparedStatement.setInt(2, ((MinAndMaxExpensesCriteria) currentCriteria).getMaxExpenses());
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Customer customer = new Customer();

                    customer.setId(resultSet.getInt("id"));
                    customer.setName(resultSet.getString("name"));
                    customer.setLastname(resultSet.getString("last_name"));

                    customersByMinAndMaxExpensesCriteria.add(customer);
                }

                allCustomersLists.add(customersByMinAndMaxExpensesCriteria);
            } else if (currentCriteria instanceof BadCustomersCountCriteria) {
                List<Customer> customersByBadCustomersCountCriteria = new ArrayList<>();

                query = "SELECT c.*, COUNT(o.id) AS total_orders " +
                        "FROM customers c " +
                        "LEFT JOIN orders o ON c.id = o.customer_id " +
                        "GROUP BY c.id " +
                        "ORDER BY total_orders ASC " +
                        "LIMIT ?;";

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setInt(1, ((BadCustomersCountCriteria) currentCriteria).getBadCustomers());
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Customer customer = new Customer();

                    customer.setId(resultSet.getInt("id"));
                    customer.setName(resultSet.getString("name"));
                    customer.setLastname(resultSet.getString("last_name"));

                    customersByBadCustomersCountCriteria.add(customer);
                }

                allCustomersLists.add(customersByBadCustomersCountCriteria);
            } else {
                throw new IllegalArgumentException("Не найден корректный критерий");
            }
        }

        return allCustomersLists;
    }
}
