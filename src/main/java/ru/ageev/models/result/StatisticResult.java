package ru.ageev.models.result;

import ru.ageev.service.Type;
import ru.ageev.models.CustomersData;

import java.util.List;

public class StatisticResult implements Result {
    private final String type = Type.stat.name();
    private long totalDays;
    private List<CustomersData> customers;
    private double totalExpenses;
    private double avgExpenses;

    public long getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(long totalDays) {
        this.totalDays = totalDays;
    }

    public List<CustomersData> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomersData> customers) {
        this.customers = customers;
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

    @Override
    public String toString() {
        return "total days: " + totalDays + "\n" + customers + "\n" + "totalExpenses: " + totalExpenses + " \n" + "avgExpenses: " + avgExpenses;
    }

    public String getType() {
        return type;
    }
}
