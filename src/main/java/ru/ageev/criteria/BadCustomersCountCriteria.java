package ru.ageev.criteria;

import ru.ageev.criteria.query.QueryCriteria;
import ru.ageev.dao.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BadCustomersCountCriteria implements Criteria {
      private int badCustomers;

      public BadCustomersCountCriteria(){}

    public BadCustomersCountCriteria(int badCustomers) {
        this.badCustomers = badCustomers;
    }

    public int getBadCustomers() {
        return badCustomers;
    }

    public void setBadCustomers(int badCustomers) {
        this.badCustomers = badCustomers;
    }

    @Override
    public List<CustomerDao> getCustomersDaoByCriteria(Connection connection, Criteria criteria) throws SQLException {

        String query = QueryCriteria.BAD_CUSTOMERS_COUNT.getQuery();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, ((BadCustomersCountCriteria) criteria).getBadCustomers());

        return getCustomersDaoByPrepareStatement(preparedStatement);
    }

    @Override
    public String toString() {
        return "criteria: bad customers";
    }
}
