package ru.ageev.service.statist;

import ru.ageev.config.DatabaseConnection;
import ru.ageev.criteria.Criteria;
import ru.ageev.criteria.StatisticCriteria;
import ru.ageev.criteria.query.QueryCriteria;
import ru.ageev.dao.CustomerDao;
import ru.ageev.dao.CustomersDataDao;
import ru.ageev.dao.ProductPurchase;
import ru.ageev.dao.StatisticByDateDao;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
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
        String startDateStr = ("2024-01-14");
        String endDateStr = ("2024-12-26");

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        StatisticByDateDao statistic = new StatisticByDateDao();

        long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println(totalDays);

        List<CustomersDataDao> customerDataDaoList = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(
                QueryCriteria.STATISTIC.getQuery());
        preparedStatement.setDate(1, Date.valueOf(startDate));
        preparedStatement.setDate(2, Date.valueOf(endDate));

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
