package org.example;
import java.util.*;
import java.time.*;

public class Member {
    int age;
    long number;
    String name;
    int issued_books = 0;
    Scanner scanner = new Scanner(System.in);
    ArrayList<Book> BookList = new ArrayList<>();
    Member(String name, int age, long number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }

    public void listAvailableBooks(Librarian theLibrarian) {
        System.out.println("Available Books:");
        for (int i = 0; i < theLibrarian.book_count; i++) {
            Book cursorBook = theLibrarian.BookList.get(i);
            if (cursorBook.issued == 0) {
                System.out.println("-----------------------------");
                System.out.printf("Book ID: %d\n", cursorBook.ID);
                System.out.printf("Name: %s\n", cursorBook.name);
                System.out.printf("Author: %s\n", cursorBook.author);
            }
        }
    }

    public void listMyBooks() {
        if (this.issued_books > 0) {
            System.out.println("My Books: ");
            for (int i = 0; i < issued_books; i++) {
                Book cursorBook = this.BookList.get(i);
                System.out.println("-----------------------------");
                System.out.printf("Book ID: %d\n", cursorBook.ID);
                System.out.printf("Name: %s\n", cursorBook.name);
                System.out.printf("Author: %s\n", cursorBook.author);
            }
        }
        else {
            System.out.println("No books issued");
        }
    }

    public void issueBook(Librarian theLibrarian) {
        if (this.issued_books < 2) {
            this.listAvailableBooks(theLibrarian);
            System.out.println("-----------------------------");
            System.out.print("Enter Book ID: ");
            int ID = scanner.nextInt();
            scanner.nextLine();
            int found = 0;
            for (int i = 0; i < theLibrarian.book_count; i++) {
                Book cursorBook = theLibrarian.BookList.get(i);
                if (cursorBook.ID == ID) {
                    found = 1;
                    if (theLibrarian.BookList.get(i).issued == 0) {
                        long penalty = this.calculatePenalty();
                        if (penalty > 0) {
                            System.out.printf("You can't issue a book as you have a penalty of $%d pending\n", penalty);
                        }
                        else {
                            theLibrarian.BookList.get(i).issued = 1;
                            theLibrarian.BookList.get(i).memberID = this.number;
                            theLibrarian.BookList.get(i).issueTime = Instant.now();
                            this.BookList.add(cursorBook);
                            this.issued_books++;
                            System.out.printf("%s issued successfully\n", cursorBook.name);
                        }
                    }
                    else {
                        System.out.printf("Book with ID %d is already issued by someone\n", ID);
                    }
                }
            }
            if (found == 0) {
                System.out.printf("Book with ID %d not found\n", ID);
            }
        }
        else {
            System.out.println("You can't issue more than 2 books");
        }
    }

    public void returnBook() {
        this.listMyBooks();
        System.out.print("\nEnter Book ID: ");
        int ID = scanner.nextInt();
        scanner.nextLine();
        int found = 0;
        for (int i = 0; i < issued_books; i++) {
            Book cursorBook = BookList.get(i);
            if (cursorBook.ID == ID) {
                long penalty = this.calculateBookPenalty(cursorBook);
                this.BookList.get(i).issued = 0;
                this.BookList.get(i).memberID = -1;
                this.BookList.get(i).issueTime = null;
                this.BookList.remove(i);
                this.issued_books--;
                System.out.printf("Book with ID %d has been returned.\n", ID);
                found = 1;
                if (penalty > 0) {
                    System.out.printf("You have paid a penalty of $%d ", penalty);
                    System.out.printf("for a delay of %d days\n", (int) (penalty/3));
                }
            }
        }
        if (found == 0) {
            System.out.printf("Book with ID %d not issued by you\n", ID);
        }
    }

    public long calculatePenalty() {
        long penalty = 0;
        Instant current_time = Instant.now();
        for (int i = 0; i < issued_books; i++) {
            Book cursorBook = this.BookList.get(i);
            Duration time_since_issue = Duration.between(cursorBook.issueTime, current_time);
            long new_time_since_issue = time_since_issue.getSeconds();
            if (new_time_since_issue > 10) {
                penalty += (new_time_since_issue - 10)*3;
            }
        }
        return penalty;
    }

    public void payPenalty() {
        long penalty = this.calculatePenalty();
        if (penalty > 0) {
            System.out.printf("You had a total penalty of $%d which has now been paid\n", penalty);
            Instant current_time = Instant.now();
            for (int i = 0; i < this.issued_books; i++) {
                this.BookList.get(i).issueTime = current_time;
            }
        }
        else {
            System.out.println("You don't have any penalty to be paid");
        }
    }

    public long calculateBookPenalty(Book book) {
        long penalty = 0;
        Instant current_time = Instant.now();
        Duration time_since_issue = Duration.between(book.issueTime, current_time);
        long new_time_since_issue = time_since_issue.getSeconds();
        if (new_time_since_issue > 10) {
            penalty = (new_time_since_issue - 10)*3;
        }
        return penalty;
    }
}