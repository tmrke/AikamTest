package ru.ageev.criteria;

import ru.ageev.criteria.query.QueryCriteria;
import ru.ageev.dao.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductNameAndCountCriteria implements Criteria {
    private String productName;
    private int minTimes;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getMinTimes() {
        return minTimes;
    }

    public void setMinTimes(int count) {
        this.minTimes = count;
    }

    @Override
    public List<CustomerDao> getCustomersDaoByCriteria(Connection connection, Criteria criteria) throws SQLException {
        String query = QueryCriteria.PRODUCT_NAME_AND_COUNT.getQuery();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, ((ProductNameAndCountCriteria) criteria).getProductName());
        preparedStatement.setInt(2, ((ProductNameAndCountCriteria) criteria).getMinTimes());

        return getCustomersDaoByPrepareStatement(preparedStatement);
    }

    @Override
    public String toString() {
        return "criteria: productName";
    }
}
