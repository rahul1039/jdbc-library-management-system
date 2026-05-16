package com.dbms.project_2341016177;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.sql.SQLException;

public class TransactionService {

    public static void processLoan(
            int memberId,
            int bookId) {

        Connection conn = null;

        Savepoint savepoint = null;

        try {

            conn = ConnectionManager.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement checkBook =
                    conn.prepareStatement(
                            "SELECT Available FROM Books " +
                            "WHERE BookID=?");

            checkBook.setInt(1, bookId);

            ResultSet rs =
                    checkBook.executeQuery();

            if (rs.next() && rs.getBoolean(1)) {

                PreparedStatement updateBook =
                        conn.prepareStatement(
                                "UPDATE Books SET Available=false " +
                                "WHERE BookID=?");

                updateBook.setInt(1, bookId);

                updateBook.executeUpdate();

                savepoint = conn.setSavepoint();

                PreparedStatement insertLoan =
                        conn.prepareStatement(
                                "INSERT INTO Loans " +
                                "(MemberID, BookID, LoanDate) " +
                                "VALUES (?, ?, CURRENT_DATE)");

                insertLoan.setInt(1, memberId);

                insertLoan.setInt(2, bookId);

                insertLoan.executeUpdate();

                PreparedStatement updateMember =
                        conn.prepareStatement(
                                "UPDATE Members " +
                                "SET ActiveLoans = ActiveLoans + 1 " +
                                "WHERE MemberID=?");

                updateMember.setInt(1, memberId);

                updateMember.executeUpdate();

                conn.commit();

                System.out.println(
                        "Loan processed successfully.");

            } else {

                System.out.println(
                        "Book not available.");
            }

        } catch (SQLException e) {

            try {

                if (conn != null) {

                    conn.rollback(savepoint);

                    conn.rollback();
                }

            } catch (SQLException ex) {

                ex.printStackTrace();
            }

            System.out.println(
                    "Transaction rolled back.");

        } finally {

            try {

                if (conn != null) {

                    conn.setAutoCommit(true);

                    conn.close();
                }

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }
}