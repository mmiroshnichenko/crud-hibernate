package org.crud.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

    private static Connection connection;

    private JdbcUtils() {

    }

    private static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
                Properties appProps = new Properties();
                appProps.load(new FileInputStream(rootPath + "application.properties"));
                String dbUrl = appProps.getProperty("db.url");
                String dbUser = appProps.getProperty("db.username");
                String dbPassword = appProps.getProperty("db.password");
                connection =  DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            } catch (IOException e) {
                throw new SQLException("Connection error");
            }
        }
        return connection;
    }

    public static synchronized PreparedStatement getPreparedStatement(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }

    public static synchronized PreparedStatement getPreparedStatementWithGeneratedKeys(String query) throws SQLException {
        return getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    }
}
