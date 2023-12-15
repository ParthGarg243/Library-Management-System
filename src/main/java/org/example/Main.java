package org.example;
import java.util.*;
import java.time.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Library Portal Initialized...");
        Scanner scanner = new Scanner(System.in);
        int choice;
        int member_choice;
        int librarian_choice;
        Librarian theLibrarian = new Librarian();

        while (true) {
            System.out.println("-----------------------------");
            System.out.println("\t\tMain Menu");
            System.out.println("1.\tEnter as a Librarian");
            System.out.println("2.\tEnter as a Member");
            System.out.println("3.\tExit");
            System.out.println("-----------------------------");
            System.out.print("Enter Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                while (true) {
                    System.out.println("-----------------------------");
                    System.out.println("1.\tRegister a Member");
                    System.out.println("2.\tRemove a Member");
                    System.out.println("3.\tAdd a Book");
                    System.out.println("4.\tRemove a Book");
                    System.out.println("5.\tView all Members along with their Books and Fines to be paid");
                    System.out.println("6.\tView all books");
                    System.out.println("7.\tBack");
                    System.out.println("-----------------------------");
                    System.out.print("Enter Choice: ");
                    librarian_choice = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("-----------------------------");
                    if (librarian_choice == 1) {
                        theLibrarian.registerMember();
                    }
                    else if (librarian_choice == 2) {
                        theLibrarian.removeMember();
                    }
                    else if (librarian_choice == 3) {
                        theLibrarian.addBook();
                    }
                    else if (librarian_choice  == 4) {
                        theLibrarian.removeBook();
                    }
                    else if (librarian_choice == 5) {
                        theLibrarian.viewMembers();
                    }
                    else if (librarian_choice == 6) {
                        theLibrarian.viewAllBooks();
                    }
                    else if (librarian_choice == 7) {
                        System.out.println("Going back to HomePage");
                        break;
                    }
                    else {
                        System.out.println("Invalid Choice");
                    }
                }
            }
            else if (choice == 2) {
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Number: ");
                long number = scanner.nextLong();
                scanner.nextLine();
                Member theMember = theLibrarian.memberLogin(name, number);
                if (theMember != null) {
                    System.out.println("-----------------------------");
                    System.out.println("Successfully logged in");
                    while (true) {
                        System.out.println("-----------------------------");
                        System.out.println("1.\tList Available Books");
                        System.out.println("2.\tList My Books");
                        System.out.println("3.\tIssue Book");
                        System.out.println("4.\tReturn Book");
                        System.out.println("5.\tPay Fine");
                        System.out.println("6.\tBack");
                        System.out.println("-----------------------------");
                        System.out.print("Enter Choice: ");
                        member_choice = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("-----------------------------");
                        if (member_choice == 1) {
                            theMember.listAvailableBooks(theLibrarian);
                        } else if (member_choice == 2) {
                            theMember.listMyBooks();
                        } else if (member_choice == 3) {
                            theMember.issueBook(theLibrarian);
                        } else if (member_choice == 4) {
                            theMember.returnBook();
                        } else if (member_choice == 5) {
                            theMember.payPenalty();
                        } else if (member_choice == 6) {
                            System.out.println("Going back to HomePage");
                            break;
                        } else {
                            System.out.println("Invalid Choice");
                        }
                    }
                }
                else {
                    System.out.println("Member with name " + name + " and number " + number + " doesn't exist");
                }
            }
            else if (choice == 3) {
                System.out.println("-----------------------------");
                System.out.println("Thanks for visiting!");
                System.out.println("-----------------------------");
                break;
            }
            else {
                System.out.println("Invalid Choice");
            }
        }
        scanner.close();
    }
}