package ru.ageev.criteria;

public class BadCustomersCount implements Criteria {
    public int getBadCustomers() {
        return badCustomers;
    }

    public void setBadCustomers(int badCustomers) {
        this.badCustomers = badCustomers;
    }

    private int badCustomers;
}
