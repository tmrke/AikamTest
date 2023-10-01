package ru.ageev.criteria.query;

public enum QueryCriteria {
    LAST_NAME("SELECT * FROM customers WHERE last_name=?"),
    PRODUCT_NAME_AND_COUNT("SELECT c.* " +
            "FROM customers c " +
            "WHERE c.id IN ( " +
            "    SELECT o.customer_id " +
            "    FROM orders o " +
            "    JOIN products p ON o.product_id = p.id " +
            "    WHERE p.name = ? " +
            "    GROUP BY o.customer_id " +
            "    HAVING COUNT(*) >= ? " +
            ");"),
    MIN_AND_MAX_EXPENSES("SELECT c.* " +
            "FROM customers c " +
            "WHERE c.id IN ( " +
            "    SELECT o.customer_id " +
            "    FROM orders o " +
            "    JOIN products p ON o.product_id = p.id " +
            "    GROUP BY o.customer_id " +
            "    HAVING SUM(p.price) BETWEEN ? AND ?" +
            ");"),
    BAD_CUSTOMERS_COUNT("SELECT c.*, COUNT(o.id) AS total_orders " +
            "FROM customers c " +
            "LEFT JOIN orders o ON c.id = o.customer_id " +
            "GROUP BY c.id " +
            "ORDER BY total_orders ASC " +
            "LIMIT ?;"),
    STATISTIC(
            "SELECT CONCAT(customers.name, ' '" +
                    ", customers.last_name) as customers" +
                    ", products.name as product_name" +
                    ", SUM(products.price) as expenses" +
                    " FROM orders " +
                    "JOIN customers ON orders.customer_id = customers.id " +
                    "JOIN products ON orders.product_id = products.id " +
                    "WHERE order_date BETWEEN ? AND ? " +
                    "AND DAYOFWEEK(order_date) NOT IN (6, 7) " +
                    "GROUP BY customers.name, products.name " +
                    "ORDER BY customers.name, expenses DESC"
    );
    private final String query;

    QueryCriteria(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
