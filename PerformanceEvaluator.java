package com.dbms.project_2341016177;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PerformanceEvaluator {

    public static void batchInsertTest(
            int count) {

        try (Connection conn =
                     ConnectionManager.getConnection()) {

            long start =
                    System.nanoTime();

            PreparedStatement ps =
                    conn.prepareStatement(
                            "INSERT INTO Members " +
                            "(Name, Age, Email) VALUES (?, ?, ?)");

            for (int i = 0; i < count; i++) {

                ps.setString(1,
                        "Member_" + i);

                ps.setInt(2,
                        20 + (i % 10));

                ps.setString(3,
                        "member" + i + "@gmail.com");

                ps.addBatch();
            }

            ps.executeBatch();

            long end =
                    System.nanoTime();

            double ms =
                    (end - start) / 1_000_000.0;

            System.out.println(
                    "Batch Insert Time: "
                            + ms + " ms");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}