package ru.ageev.searcher;

import ru.ageev.config.DatabaseConnection;
import ru.ageev.criteria.*;
import ru.ageev.models.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Searcher {
    private final Connection connection = DatabaseConnection.getConnection();
    private List<Criteria> criteriaList;
    private List<List<Customer>> allCustomersLists = new ArrayList<>();

    public Searcher(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public List<List<Customer>> getCustomersLists() throws SQLException {
        for (Criteria currentCriteria : criteriaList) {
            allCustomersLists.add(currentCriteria.getCustomersByCriteria(connection, currentCriteria));
        }

        return allCustomersLists;
    }
}
