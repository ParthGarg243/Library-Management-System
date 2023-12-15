package org.example;
import java.util.*;
import java.time.*;

public class Librarian {
    int ID_counter = 0;
    int book_count = 0;
    int member_count = 0;
    Scanner scanner = new Scanner(System.in);
    ArrayList<Book> BookList = new ArrayList<>();
    ArrayList<Member> MemberList = new ArrayList<>();

    int idChecker(long ID) {
        int found = 0;
        for (int i = 0; i < this.member_count; i++) {
            if (this.MemberList.get(i).number == ID) {
                found = 1;
                break;
            }
        }
        return found;
    }

    public void addBook() {
        System.out.print("Enter Book Title: ");
        String name = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author  = scanner.nextLine();
        System.out.print("Enter copies: ");
        int copies = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < copies; i++) {
            this.ID_counter++;
            this.book_count++;
            Book book = new Book(ID_counter, name, author);
            this.BookList.add(book);
        }
        System.out.println("Books have been added successfully");
    }

    public void removeBook() {
        int found = 0;
        System.out.print("Enter Book ID: ");
        int ID = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < this.book_count; i++) {
            Book cursorBook = this.BookList.get(i);
            if (cursorBook.ID == ID) {
                found = 1;
                if (cursorBook.issued == 0) {
                    this.BookList.remove(i);
                    this.book_count--;
                    System.out.printf("Book with ID %d has been removed\n", cursorBook.ID);
                    break;
                }
                else if (cursorBook.issued == 1){
                    System.out.printf("Can't remove book with ID %d as it has not been returned yet\n", ID);
                }
            }
        }
        if (found == 0) {
            System.out.println("Book not Found");
        }
    }

    public void viewAllBooks() {
        if (this.book_count > 0) {
            System.out.println("Books:");
            for (int i = 0; i < this.book_count; i++) {
                Book cursorBook = this.BookList.get(i);
                System.out.println("-----------------------------");
                System.out.printf("Book ID: %d\n", cursorBook.ID);
                System.out.printf("Name: %s\n", cursorBook.name);
                System.out.printf("Author: %s\n", cursorBook.author);
                if (cursorBook.issued == 0) {
                    System.out.println("Issued: No");
                } else if (cursorBook.issued == 1) {
                    System.out.println("Issued: Yes");
                    System.out.printf("Issued by Member ID: %d\n", cursorBook.memberID);
                }
            }
        }
        else if (this.book_count == 0) {
            System.out.println("No added books");
        }
    }

    public void registerMember() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter phone number: ");
        long number = scanner.nextLong();
        scanner.nextLine();
        int found = idChecker(number);
        if (found == 0) {
            Member member = new Member(name, age, number);
            this.MemberList.add(member);
            System.out.printf("Member successfully registered with Member ID: %d\n", number);
            this.member_count++;
        }
        else if (found == 1){
            System.out.printf("Member already exists with Member ID: %d\n", number);
        }
    }

    public void removeMember(){
        System.out.print("Enter ID: ");
        long number = scanner.nextLong();
        scanner.nextLine();
        int found = idChecker(number);
        if (found == 0) {
            System.out.printf("No member exists with Member ID: %d\n", number);
        }
        else if (found == 1) {
            for (int i = 0; i < member_count; i++) {
                Member cursorMember = this.MemberList.get(i);
                if (cursorMember.number == number) {
                    if (cursorMember.issued_books == 0) {
                        this.MemberList.remove(i);
                        this.member_count--;
                        System.out.printf("Member with member with Member ID: %d has been removed\n", number);
                    }
                    else {
                        System.out.printf("Member with Member ID %d can't be removed as they haven't returned their issued books\n", number);
                    }
                    break;
                }
            }
        }
    }

    public Member memberLogin(String name, long number) {
        for (int i = 0; i < this.member_count; i++) {
            Member cursorMember = this.MemberList.get(i);
            if ((name.equalsIgnoreCase(cursorMember.name)) && (cursorMember.number == number)) {
                return cursorMember;
            }
        }
        return null;
    }

    public void viewMembers() {
        if (this.member_count > 0) {
            System.out.println("Registered Members:");
            for (int i = 0; i < this.member_count; i++) {
                Member cursorMember = this.MemberList.get(i);
                System.out.println("-----------------------------");
                System.out.printf("Member #%d:\n", i + 1);
                System.out.println("-----------");
                System.out.printf("Name: %s\n", cursorMember.name);
                System.out.printf("Age: %d\n", cursorMember.age);
                System.out.printf("Number: %d\n", cursorMember.number);
                System.out.println("\nIssued Books:");
                for (int j = 0; j < cursorMember.issued_books; j++) {
                    Book cursorBook = cursorMember.BookList.get(j);
                    System.out.printf("\nBook ID: %d\n", cursorBook.ID);
                    System.out.printf("Name: %s\n", cursorBook.name);
                    System.out.printf("Author: %s\n", cursorBook.author);
                }
                System.out.printf("\nFine to be paid: $%d\n", cursorMember.calculatePenalty());
            }
        }
        else if (this.member_count == 0) {
            System.out.println("No registered members");
        }
    }
}