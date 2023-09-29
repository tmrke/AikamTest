package ru.ageev.criteria;

public class MinAndMaxExpensesCriteria implements Criteria {
    private int minExpenses;
    private int maxExpenses;
    public int getMinExpenses() {
        return minExpenses;
    }

    public void setMinExpenses(int minExpenses) {
        this.minExpenses = minExpenses;
    }

    public int getMaxExpenses() {
        return maxExpenses;
    }

    public void setMaxExpenses(int maxExpenses) {
        this.maxExpenses = maxExpenses;
    }
}
