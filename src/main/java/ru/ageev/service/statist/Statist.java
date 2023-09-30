package ru.ageev.service.statist;

import ru.ageev.config.DatabaseConnection;
import ru.ageev.criteria.Criteria;
import ru.ageev.criteria.StatisticCriteria;
import ru.ageev.dao.CustomerDao;
import ru.ageev.dao.CustomersDataDao;
import ru.ageev.dao.ProductPurchase;
import ru.ageev.dao.StatisticByDateDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Statist {
    private final Connection connection = DatabaseConnection.getConnection();
    private StatisticCriteria statisticCriteria;

    public Statist(StatisticCriteria criteria) {
        statisticCriteria = criteria;
    }

    public Statist() {
    }

    public void getStatistic() throws SQLException {
//        String startDate = "2024-01-14";
//        String endDate = "2024-12-26";
//
//        StatisticByDateDao statistic = new StatisticByDateDao();
//
//        int totalDays = (int) ChronoUnit.DAYS.between(Instant.ofEpochMilli(Long.parseLong(startDate)), Instant.ofEpochMilli(Long.parseLong(endDate)));
//        statistic.setTotalDays(totalDays);

        StatisticByDateDao statistic = new StatisticByDateDao();
        List<CustomersDataDao> customerDataDaoList = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT CONCAT(customers.name, ' '" +
                        ", customers.last_name) as customers" +
                        ", products.name as product_name" +
                        ", SUM(products.price) as expenses" +
                        " FROM orders " +
                        "JOIN customers ON orders.customer_id = customers.id " +
                        "JOIN products ON orders.product_id = products.id " +
                        "WHERE order_date BETWEEN '2024-01-14' AND '2024-12-26' " +
                        "GROUP BY customers.id, products.name " +
                        "ORDER BY expenses DESC");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            ProductPurchase productPurchase = new ProductPurchase();
            productPurchase.setExpenses(resultSet.getDouble("expenses"));
            productPurchase.setName(resultSet.getString("product_name"));

            CustomersDataDao customersDataDao = new CustomersDataDao();
            customersDataDao.setName(resultSet.getString("customers"));
            customersDataDao.setPurchases(new ArrayList<>());
            customersDataDao.getPurchases().add(productPurchase);

            System.out.println(customersDataDao.getName() + Arrays.toString(customersDataDao.getPurchases().toArray()));

            customerDataDaoList.add(customersDataDao);
        }


        //return statistic;
    }
}
