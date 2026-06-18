package model;

import service.Borrowable;

public class Book implements Borrowable {
    private String bookID;
    private String name;
    private String author;
    private int publishDate;
    private int quantity;
    private boolean referenceOnly;

    private String currentBorrowerId; // null neu chua ai muon 
    private String borrowDate; 

    public Book(String bookID, String name, String author) {
        this(bookID, name, author, 0, 1, false, null);
    }

    public Book(String bookID, String name, String author, int publishDate, int quantity, boolean referenceOnly) {
        this(bookID, name, author, publishDate, quantity, referenceOnly, null);
    }

    public Book(String bookID, String name, String author, int publishDate, int quantity, boolean referenceOnly, String currentBorrowerId) {
        this.bookID = bookID;
        this.name = name;
        this.author = author;
        this.publishDate = publishDate;
        this.quantity = quantity;
        this.referenceOnly = referenceOnly;
        this.currentBorrowerId = null;
    }

    public String getBookID() {
        return bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(int publishDate) {
        this.publishDate = publishDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isReferenceOnly() {
        return referenceOnly;
    }

    public void increaseQuantity() {
            quantity++;
    }

    public void decreaseQuantity() {
        if (this.quantity > 0) {
            quantity--;
        } else {
            System.out.println("Khong du so luong sach.");
        }
    }

    @Override 
    public void borrowBy(String readerId, String date) { 
        if (!isAvailable()) { 
            System.out.println("Book '" + name + "' is not available."); 
            return; 
        } 

        this.currentBorrowerId = readerId; 
        this.borrowDate        = date; 

        System.out.println("Book '" + name + "' borrowed by " + readerId); 
    } 

    @Override 
    public void returnBook(String date) { 
        System.out.println("Book '" + name + "' returned on " + date); 

        this.currentBorrowerId = null; 
        this.borrowDate        = null; 
    } 

    @Override 
    public boolean isAvailable() { return currentBorrowerId == null; } 

    @Override 
    public String getBorrowerId() { return currentBorrowerId; } 

    public void showInfo() {
        System.out.println("ID: " + bookID);
        System.out.println("Ten: " + name);
        System.out.println("Tac gia: " + author);
        System.out.println("Ngay xuat ban: " + publishDate);
        System.out.println("So luong: " + quantity);
        System.out.println("Sach tham khao: " + (referenceOnly ? "Co" : "Khong"));
    }
}
