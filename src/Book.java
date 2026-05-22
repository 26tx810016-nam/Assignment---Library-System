public class Book {
    private String bookID;
    private String name;
    private String author;
    private int publishDate;
    private int quantity;

    public Book(String bookID, String name, String author, int publishDate, int quantity) {
        this.bookID = bookID;
        this.name = name;
        this.author = author;
        this.publishDate = publishDate;
        this.quantity = quantity;
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
    }
}