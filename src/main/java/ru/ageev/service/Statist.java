package ru.ageev.service;

import ru.ageev.config.DatabaseConnection;
import ru.ageev.criteria.StatisticCriteria;
import ru.ageev.criteria.query.QueryCriteria;
import ru.ageev.models.CustomersData;
import ru.ageev.models.ProductPurchase;
import ru.ageev.models.result.StatisticResult;

import java.sql.*;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class Statist {
    private final Connection connection = DatabaseConnection.getConnection();
    private final StatisticCriteria statisticCriteria;

    public Statist(StatisticCriteria criteria) {
        statisticCriteria = criteria;
    }

    public StatisticResult getStatistic() throws SQLException {
        Date startDate = statisticCriteria.getStartDate();
        Date endDate = statisticCriteria.getEndDate();

        StatisticResult statistic = new StatisticResult();
        statistic.setTotalDays(getTotalDays(startDate, endDate));

        PreparedStatement preparedStatement = connection.prepareStatement(
                QueryCriteria.STATISTIC.getQuery());
        preparedStatement.setDate(1, startDate);
        preparedStatement.setDate(2, endDate);

        ResultSet resultSet = preparedStatement.executeQuery();

        Map<String, CustomersData> customersDataMap = new HashMap<>();

        while (resultSet.next()) {
            String customerName = resultSet.getString("customers");
            String productName = resultSet.getString("product_name");
            double price = resultSet.getDouble("expenses");

            if (!customersDataMap.containsKey(customerName)) {
                customersDataMap.put(customerName, new CustomersData(customerName));
            }

            CustomersData customersData = customersDataMap.get(customerName);
            customersData.getPurchases().add(new ProductPurchase(productName, price));
        }

        customersDataMap.forEach((key, value) -> value.setTotalExpenses(getCustomerTotalExpenses(value.getPurchases())));

        statistic.setCustomers(sortByTotalExpenses(customersDataMap));
        statistic.setTotalExpenses(getAllTotalExpenses(customersDataMap));
        statistic.setAvgExpenses(getAvgExpenses(customersDataMap));

        return statistic;
    }

    private long getTotalDays(Date sqlStartDate, Date sqlEndDate) {
        long weekdaysCount = 0;

        LocalDate startDate = sqlStartDate.toLocalDate();
        LocalDate endDate = sqlEndDate.toLocalDate();
        LocalDate currentData = startDate;

        while (currentData.isBefore(endDate)) {
            if (currentData.getDayOfWeek() != DayOfWeek.SATURDAY && currentData.getDayOfWeek() != DayOfWeek.SUNDAY) {
                weekdaysCount++;
            }

            currentData = currentData.plusDays(1);
        }

        return weekdaysCount;
    }

    private double getCustomerTotalExpenses(List<ProductPurchase> purchases) {
        double totalExpenses = 0;

        for (ProductPurchase productPurchase : purchases) {
            totalExpenses += productPurchase.getExpenses();
        }

        return totalExpenses;
    }

    private double getAllTotalExpenses(Map<String, CustomersData> customersDataMap) {
        double totalExpenses = 0;

        for (Map.Entry<String, CustomersData> entry : customersDataMap.entrySet()) {
            totalExpenses += entry.getValue().getTotalExpenses();
        }

        return totalExpenses;
    }

    private double getAvgExpenses(Map<String, CustomersData> customersDataMap) {
        return getAllTotalExpenses(customersDataMap) / customersDataMap.size();
    }

    private List<CustomersData> sortByTotalExpenses(Map<String, CustomersData> customersDataMap) {
        List<Map.Entry<String, CustomersData>> entryList = new ArrayList<>(customersDataMap.entrySet());

        entryList.sort(
                (o1, o2) -> {
                    double totalExpenses1 = o1.getValue().getTotalExpenses();
                    double totalExpenses2 = o2.getValue().getTotalExpenses();

                    return Double.compare(totalExpenses2, totalExpenses1);
                }
        );

        List<CustomersData> customers = new ArrayList<>();

        for (Map.Entry<String, CustomersData> entry : entryList) {
            customers.add(entry.getValue());
        }

        return customers;
    }
}
