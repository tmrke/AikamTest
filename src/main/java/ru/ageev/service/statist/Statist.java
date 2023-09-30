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
import java.time.temporal.Temporal;
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
        Date startDate = statisticCriteria.getStartDate();
        Date endDate = statisticCriteria.getEndDate();

        StatisticByDateDao statistic = new StatisticByDateDao();
        statistic.setTotalDays(getTotalDays(startDate, endDate));

        List<CustomersDataDao> customerDataDaoList = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(
                QueryCriteria.STATISTIC.getQuery());
        preparedStatement.setDate(1, startDate);
        preparedStatement.setDate(2, endDate);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            List<ProductPurchase> productPurchases = new ArrayList<>();
            ProductPurchase productPurchase = new ProductPurchase();
            productPurchase.setExpenses(resultSet.getDouble("expenses"));
            productPurchase.setName(resultSet.getString("product_name"));
            productPurchases.add(productPurchase);

            //TODO записывает только 1 продукт, а не все

            CustomersDataDao customersDataDao = new CustomersDataDao();
            customersDataDao.setName(resultSet.getString("customers"));
            customersDataDao.setPurchases(new ArrayList<>());
            customersDataDao.setPurchases(productPurchases);

            System.out.println(customersDataDao.getName() + Arrays.toString(customersDataDao.getPurchases().toArray()));

            customerDataDaoList.add(customersDataDao);
        }

        //return statistic;
    }

    private long getTotalDays(Date sqlStartDate, Date sqlEndDate) {
        LocalDate startDate = sqlStartDate.toLocalDate();
        LocalDate endDate = sqlEndDate.toLocalDate();

        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}
