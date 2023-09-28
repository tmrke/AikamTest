package ru.ageev.criteria;

public class LastNameCriteria implements Criteria {
    private String customerLastName;

    public String getLastName() {
        return customerLastName;
    }

    public void setLastName(String lastName) {
        this.customerLastName = lastName;
    }
}
