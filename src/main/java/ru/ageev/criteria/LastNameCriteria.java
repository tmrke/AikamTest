package ru.ageev.criteria;

import ru.ageev.criteria.query.QueryCriteria;
import ru.ageev.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LastNameCriteria implements Criteria {
    private String customerLastName;

    public String getLastName() {
        return customerLastName;
    }

    public void setLastName(String lastName) {
        this.customerLastName = lastName;
    }

    @Override
    public List<Customer> getCustomersByCriteria(Connection connection, Criteria criteria) throws SQLException {
        String query = QueryCriteria.LAST_NAME.getQuery();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, ((LastNameCriteria) criteria).getLastName());
        return getCustomersByPrepareStatement(preparedStatement);
    }
}
