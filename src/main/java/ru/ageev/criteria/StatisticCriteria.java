package ru.ageev.criteria;

import ru.ageev.criteria.query.QueryCriteria;
import ru.ageev.dao.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class StatisticCriteria implements Criteria {
    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    @Override
    public List<CustomerDao> getCustomersDaoByCriteria(Connection connection, Criteria criteria) throws SQLException {
        String query =
                "SELECT orders.customer_id as customer, SUM(products.price) as expenses " +
                        "FROM orders " +
                        "JOIN customers ON orders.customer_id = customer.id " +
                        "JOIN products ON orders.product_id = products.id " +
                        "WHERE order_date BETWEEN ? AND ? " +
                        "GROUP BY customers.id" +
                        "ORDER BY customers.id, expenses DESC";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, (java.sql.Date) ((StatisticCriteria) criteria).getStartDate());
        preparedStatement.setDate(2, (java.sql.Date) ((StatisticCriteria) criteria).getEndDate());

        return getCustomersDaoByPrepareStatement(preparedStatement);
    }
}
