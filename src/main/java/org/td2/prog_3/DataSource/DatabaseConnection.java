package org.td2.prog_3.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/collectivite_agricole_db";
    private static final String USER = "collectivite_agricole_user";
    private static final String PASSWORD = "123456";

    public  Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
