package model;

public class Book {
    private String bookID;
    private String name;
    private String author;
    private int publishDate;
    private int quantity;
    private boolean referenceOnly;

    public Book(String bookID, String name, String author, int publishDate, int quantity) {
        this(bookID, name, author, publishDate, quantity, false);
    }

    public Book(String bookID, String name, String author, int publishDate, int quantity, boolean referenceOnly) {
        this.bookID = bookID;
        this.name = name;
        this.author = author;
        this.publishDate = publishDate;
        this.quantity = quantity;
        this.referenceOnly = referenceOnly;
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

    public void showInfo() {
        System.out.println("ID: " + bookID);
        System.out.println("Ten: " + name);
        System.out.println("Tac gia: " + author);
        System.out.println("Ngay xuat ban: " + publishDate);
        System.out.println("So luong: " + quantity);
        System.out.println("Sach tham khao: " + (referenceOnly ? "Co" : "Khong"));
    }
}
