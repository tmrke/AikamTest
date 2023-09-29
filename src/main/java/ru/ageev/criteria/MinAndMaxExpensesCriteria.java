package ru.ageev.criteria;

import ru.ageev.criteria.query.QueryCriteria;
import ru.ageev.dao.CustomerDao;
import ru.ageev.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MinAndMaxExpensesCriteria implements Criteria {
    private int minExpenses;
    private int maxExpenses;

    public int getMinExpenses() {
        return minExpenses;
    }

    public void setMinExpenses(int minExpenses) {
        this.minExpenses = minExpenses;
    }

    public int getMaxExpenses() {
        return maxExpenses;
    }

    public void setMaxExpenses(int maxExpenses) {
        this.maxExpenses = maxExpenses;
    }

    @Override
    public List<CustomerDao> getCustomersByCriteria(Connection connection, Criteria criteria) throws SQLException {
        String query = QueryCriteria.MIN_AND_MAX_EXPENSES.getQuery();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, ((MinAndMaxExpensesCriteria) criteria).getMinExpenses());
        preparedStatement.setInt(2, ((MinAndMaxExpensesCriteria) criteria).getMaxExpenses());

        return getCustomersByPrepareStatement(preparedStatement);
    }

    @Override
    public String toString() {
        return "minExpenses";
    }
}
