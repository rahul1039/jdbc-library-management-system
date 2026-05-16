package com.dbms.project_2341016177;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        DatabaseSetup.initializeDatabase();

        Scanner sc =
                new Scanner(System.in);

        while (true) {

            System.out.println(
                    "\n===== LIBRARY MENU =====");

            System.out.println(
                    "1. Add Member");

            System.out.println(
                    "2. Add Book");

            System.out.println(
                    "3. Process Loan");

            System.out.println(
                    "4. Performance Test");

            System.out.println(
                    "5. Exit");

            System.out.print(
                    "Enter choice: ");

            int choice =
                    sc.nextInt();

            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print(
                            "Enter member name: ");

                    String memberName =
                            sc.nextLine();

                    System.out.print(
                            "Enter age: ");

                    int age =
                            sc.nextInt();

                    sc.nextLine();

                    System.out.print(
                            "Enter email: ");

                    String email =
                            sc.nextLine();

                    BusinessLogic.addMember(
                            memberName,
                            age,
                            email);

                    break;

                case 2:

                    System.out.print(
                            "Enter book title: ");

                    String title =
                            sc.nextLine();

                    System.out.print(
                            "Enter ISBN: ");

                    String isbn =
                            sc.nextLine();

                    BusinessLogic.addBook(
                            title,
                            isbn);

                    break;

                case 3:

                    System.out.print(
                            "Enter Member ID: ");

                    int memberId =
                            sc.nextInt();

                    System.out.print(
                            "Enter Book ID: ");

                    int bookId =
                            sc.nextInt();

                    TransactionService.processLoan(
                            memberId,
                            bookId);

                    break;

                case 4:

                    PerformanceEvaluator
                            .batchInsertTest(1000);

                    break;

                case 5:

                    ConnectionManager.shutdown();

                    System.exit(0);

                default:

                    System.out.println(
                            "Invalid choice.");
            }
        }
    }
}