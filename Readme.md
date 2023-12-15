# Library Management System

This is a Library Management System developed as part of the Assignment 1 of CSE201: Advanced Programming conducted in Monsoon 2023 at IIIT Delhi.

## Features

- Menu-driven interface for users to interact with the library system.
- The project has 4 classes: `Main`, `Librarian`, `Member`, and `Book`.
- The `Librarian` class has the following functionalities: adding a book, removing a book, viewing all books, viewing the data of all members including the books issued by them and the fine they need to pay, registering a member, and removing a member.
- The `Member` class has the following functionalities: viewing all available books, viewing books issued by the member, issuing a book, returning a book, and paying their fine.
- The `Main` class has the main menu and the menu for both the librarian and the member, and it calls functions according to user input.
- Please note the following regarding the fine system: The user pays the fine for their issued book while returning it if there is any fine due for it. The user pays the fine for all the books that they have issued at that moment if there exists any fine for them.

## Instructions
1. Navigate to the root directory.
2. Execute the following commands:
    - mvn clean
    - mvn compile
    - mvn package
    - java -cp .\target\AP_Assignment_1-1.0-SNAPSHOT.jar org.example.Main