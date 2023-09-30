package ru.ageev.dao;

import java.util.ArrayList;
import java.util.List;

public class CustomersDataDao {
    private String name;
    private List<ProductPurchase> purchases;
    private double totalExpenses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductPurchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<ProductPurchase> purchases) {
        this.purchases = purchases;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
}

