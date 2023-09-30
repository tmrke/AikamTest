package ru.ageev.service.searcher;

import ru.ageev.config.DatabaseConnection;
import ru.ageev.criteria.*;
import ru.ageev.dao.CustomerDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class Searcher {
    private final Connection connection = DatabaseConnection.getConnection();
    private final List<Criteria> criteriaList;
    private final LinkedHashMap<Criteria, List<CustomerDao>> criteriaMap = new LinkedHashMap<>();

    public Searcher(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public LinkedHashMap<Criteria, List<CustomerDao>> getCustomersDaoByCriteria() throws SQLException {
        for (Criteria criteria : criteriaList) {
            List<CustomerDao> customersDaoByCriteria = criteria.getCustomersDaoByCriteria(connection, criteria);
            criteriaMap.put(criteria, customersDaoByCriteria);
        }

        return criteriaMap;
    }
}
