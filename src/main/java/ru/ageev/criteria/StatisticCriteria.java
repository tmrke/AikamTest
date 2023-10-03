package ru.ageev.criteria;

import ru.ageev.criteria.query.QueryCriteria;
import ru.ageev.dao.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public class StatisticCriteria implements Criteria {
    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    @Override
    public List<CustomerDao> getCustomersDaoByCriteria(Connection connection, Criteria criteria) throws SQLException {
        String query = QueryCriteria.STATISTIC.getQuery();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, ((StatisticCriteria) criteria).getStartDate());
        preparedStatement.setDate(2, ((StatisticCriteria) criteria).getEndDate());

        return getCustomersDaoByPrepareStatement(preparedStatement);
    }
}