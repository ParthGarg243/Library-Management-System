package org.example;
import java.util.*;
import java.time.*;

public class Book {
    int ID;
    String name;
    String author;
    long memberID = -1;
    int issued = 0;
    Instant issueTime;

    Book(int count, String name, String author) {
        this.ID = count;
        this.name = name;
        this.author = author;
    }
}