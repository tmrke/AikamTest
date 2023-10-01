package ru.ageev.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomersData {
    private String name;
    private List<ProductPurchase> purchases;
    private double totalExpenses;

    public CustomersData(String name){
        this.name = name;
        purchases = new ArrayList<>();
    }

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

    @Override
    public String toString(){
        return name + ": " + Arrays.toString(purchases.toArray()) + ", total expenses: " + totalExpenses + "\n";
    }
}

