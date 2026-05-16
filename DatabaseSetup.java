package com.dbms.project_2341016177;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseSetup {

    public static void initializeDatabase() {

        try (Connection conn =
                     ConnectionManager.getConnection();

             Statement stmt =
                     conn.createStatement()) {

            stmt.executeUpdate(
                    "CREATE TABLE Members (" +
                    "MemberID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "Name VARCHAR(100), " +
                    "Age INT, " +
                    "Email VARCHAR(100) UNIQUE, " +
                    "ActiveLoans INT DEFAULT 0)"
            );

            stmt.executeUpdate(
                    "CREATE TABLE Books (" +
                    "BookID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "Title VARCHAR(100), " +
                    "ISBN VARCHAR(20) UNIQUE, " +
                    "Available BOOLEAN)"
            );

            stmt.executeUpdate(
                    "CREATE TABLE Loans (" +
                    "LoanID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "MemberID INT REFERENCES Members(MemberID), " +
                    "BookID INT REFERENCES Books(BookID), " +
                    "LoanDate DATE, " +
                    "ReturnDate DATE)"
            );

            stmt.executeUpdate(
                    "CREATE INDEX idx_isbn ON Books(ISBN)"
            );

            stmt.executeUpdate(
                    "CREATE INDEX idx_member ON Loans(MemberID)"
            );

            System.out.println(
                    "Database initialized successfully.");

        } catch (SQLException e) {

            if ("X0Y32".equals(e.getSQLState())) {

                System.out.println(
                        "Tables already exist.");

            } else {

                e.printStackTrace();
            }
        }
    }
}