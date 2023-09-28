package ru.ageev.criteria;

public class ProductNameAndCountCriteria implements Criteria {
    private String productName;
    private int minTimes;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getMinTimes() {
        return minTimes;
    }

    public void setMinTimes(int count) {
        this.minTimes = count;
    }
}
