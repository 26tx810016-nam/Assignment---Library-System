package model;

import java.time.LocalDate;
import service.Returnable;
import service.Borrowable;

public class BorrowSlip implements Returnable, Borrowable {
    private Reader reader;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowSlip(Reader reader, Book book) {
        this.reader = reader;
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    @Override
    public String getReturnDate() {
        return returnDate == null ? null : returnDate.toString();
    }

    @Override
    public void confirmReturn(String date) {
        if (date == null) {
            this.returnDate = null;
            return;
        }
        this.returnDate = LocalDate.parse(date);
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean isReturned() {
        return returnDate != null;
    }

    // Borrowable interface methods
    @Override
    public void borrowBy(String readerId, String date) {
        this.borrowDate = LocalDate.parse(date);
    }

    @Override
    public void returnBook(String date) {
        confirmReturn(date);
    }

    @Override
    public boolean isAvailable() {
        return returnDate != null; // Available if returned
    }

    @Override
    public String getBorrowerId() {
        return reader != null ? reader.getReaderID() : "Unknown";
    }

    public void showSlip() {
        System.out.println("---Phieu muon sach---");
        System.out.println("Doc gia: " + reader.getFullName() + " (" + reader.getReaderID() + ")");
        System.out.println("Sach: " + book.getName() + " (" + book.getBookID() + ")");
        System.out.println("Ngay muon: " + borrowDate);
        System.out.println("Tinh trang: " + (returnDate != null ? "Da tra" : "Chua tra"));
    }
}

