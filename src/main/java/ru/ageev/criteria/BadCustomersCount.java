package ru.ageev.criteria;

public class BadCustomersCount implements Criteria {
    private int badCustomers;

    public int getBadCustomers() {
        return badCustomers;
    }

    public void setBadCustomers(int badCustomers) {
        this.badCustomers = badCustomers;
    }
}
