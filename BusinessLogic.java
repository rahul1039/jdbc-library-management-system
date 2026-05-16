package com.dbms.project_2341016177;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BusinessLogic {

    public static void addMember(
            String name,
            int age,
            String email) {

        try (Connection conn =
                     ConnectionManager.getConnection();

             PreparedStatement ps =
                     conn.prepareStatement(
                             "INSERT INTO Members " +
                             "(Name, Age, Email) " +
                             "VALUES (?, ?, ?)")) {

            ps.setString(1, name);

            ps.setInt(2, age);

            ps.setString(3, email);

            ps.executeUpdate();

            System.out.println(
                    "Member added successfully.");

        } catch (SQLException e) {

            System.out.println(
                    "Error adding member.");

            e.printStackTrace();
        }
    }

    public static void addBook(
            String title,
            String isbn) {

        try (Connection conn =
                     ConnectionManager.getConnection();

             PreparedStatement ps =
                     conn.prepareStatement(
                             "INSERT INTO Books " +
                             "(Title, ISBN, Available) " +
                             "VALUES (?, ?, true)")) {

            ps.setString(1, title);

            ps.setString(2, isbn);

            ps.executeUpdate();

            System.out.println(
                    "Book added successfully.");

        } catch (SQLException e) {

            System.out.println(
                    "Error adding book.");

            e.printStackTrace();
        }
    }
}