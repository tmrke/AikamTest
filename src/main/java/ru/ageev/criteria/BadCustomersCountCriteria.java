package ru.ageev.criteria;

public class BadCustomersCountCriteria implements Criteria {
    private int badCustomers;

    public int getBadCustomers() {
        return badCustomers;
    }

    public void setBadCustomers(int badCustomers) {
        this.badCustomers = badCustomers;
    }
}