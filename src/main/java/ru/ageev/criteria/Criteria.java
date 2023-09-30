package ru.ageev.criteria;

import ru.ageev.dao.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Criteria {
    List<CustomerDao> getCustomersDaoByCriteria(Connection connection, Criteria criteria) throws SQLException;

    default List<CustomerDao> getCustomersDaoByPrepareStatement(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();

        List<CustomerDao> customersDao = new ArrayList<>();

        while (resultSet.next()) {
            CustomerDao customerDao = new CustomerDao();

            customerDao.setId(resultSet.getInt("id"));
            customerDao.setName(resultSet.getString("name"));
            customerDao.setLastname(resultSet.getString("last_name"));
            customersDao.add(customerDao);
        }

        return customersDao;
    }
}
