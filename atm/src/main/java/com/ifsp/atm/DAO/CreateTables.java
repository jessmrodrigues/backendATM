package com.ifsp.atm.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class CreateTables {
    public static void main(String[] argv) throws SQLException {
        // Connection Configuration
        Properties connConfig = new Properties();
        connConfig.setProperty("user", "root");
        connConfig.setProperty("password", "isabelly1611");

        // Create Connection to MariaDB Enterprise
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", connConfig)) {

            // Disable Auto-Commit
            conn.setAutoCommit(false);

            // user
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS atm.Account ("
                                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                                + "name VARCHAR(50),"
                                + "pin VARCHAR(50),"
                                + "balance VARCHAR(50))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // publisher
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS atm.Transactions ("
                                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                                + "accountId INT,"
                                + "balance VARCHAR(50),"
                                + "type VARCHAR(50),"
                                + "CONSTRAINT accountId FOREIGN KEY (accountId)"
                                + " REFERENCES atm.Account(id))"
                                + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
