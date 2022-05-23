package org.crud.repository.impl;

import java.sql.*;

public abstract class MysqlGenericRepository {
    protected static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/crud-jdbc";

    private static final String USER = "root";
    private static final String PASS = "elhI;Ylx,3))";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
