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
            List<Customer> customersByLastNameCriteria = new ArrayList<>();

            if (currentCriteria instanceof LastNameCriteria) {

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
//            } else if (currentCriteria instanceof ProductNameAndCountCriteria) {
//                query = "SELECT * FROM customers WHERE last_name='Иванов'";
//
//                PreparedStatement preparedStatement = connection.prepareStatement(query);
////                preparedStatement.setString(1, ((ProductNameAndCountCriteria) currentCriteria));
//                resultSet = preparedStatement.executeQuery();
//            } else if (currentCriteria instanceof MinAndMaxExpensesCriteria) {
//                query = "SELECT * FROM customers WHERE last_name='Петров'";
//
//                PreparedStatement preparedStatement = connection.prepareStatement(query);
////                preparedStatement.setString(1, ((MinAndMaxExpensesCriteria) currentCriteria));
//                resultSet = preparedStatement.executeQuery();
//            } else if (currentCriteria instanceof BadCustomersCountCriteria) {
//                query = "SELECT * FROM customers WHERE last_name='Сидоров'";
//
//                PreparedStatement preparedStatement = connection.prepareStatement(query);
////                preparedStatement.setString(1, ((BadCustomersCountCriteria) currentCriteria).getLastName());
//                resultSet = preparedStatement.executeQuery();
//
            } else {
                throw new IllegalArgumentException("Не найден корректный критерий");
            }
        }

        return allCustomersLists;
    }
}
