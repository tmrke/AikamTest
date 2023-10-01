package ru.ageev.criteria;

import ru.ageev.dao.CustomerDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ErrorCriteria implements Criteria {
       private String message;

    public ErrorCriteria(String message) {
        this.message = message;
    }

    public ErrorCriteria() {
    }

    @Override
    public List<CustomerDao> getCustomersDaoByCriteria(Connection connection, Criteria criteria) throws SQLException {
        return null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
