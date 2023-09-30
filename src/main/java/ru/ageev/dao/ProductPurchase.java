package ru.ageev.dao;

import ru.ageev.models.Customer;

public class ProductPurchase {
    private String name;
    private double expenses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return name + " " + expenses;
    }
}
