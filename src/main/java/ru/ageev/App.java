package ru.ageev;


import config.DatabaseConnection;
import ru.ageev.criteria.Criteria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        String uri = "src/main/resources/criteria/criteria1.json";

        JsonParser jsonParser = new JsonParser();

        List<Criteria> criteria = jsonParser.getCriteriaList(uri);
        Connection connection = DatabaseConnection.getConnection();
    }
}



