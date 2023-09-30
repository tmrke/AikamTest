package ru.ageev.dao;

import java.util.List;

public class StatisticByDateDao {
    private long totalDays;
    private CustomersDataDao customersDataDao;
    private double totalExpenses;
    private double avgExpenses;

    public long getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(long totalDays) {
        this.totalDays = totalDays;
    }

    public CustomersDataDao getCustomersDataDao() {
        return customersDataDao;
    }

    public void setCustomersDataDao(CustomersDataDao customersDataDao) {
        this.customersDataDao = customersDataDao;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public double getAvgExpenses() {
        return avgExpenses;
    }

    public void setAvgExpenses(double avgExpenses) {
        this.avgExpenses = avgExpenses;
    }
}

