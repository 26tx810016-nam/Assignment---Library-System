import java.time.LocalDate;

public class BorrowSlip {
    private Reader reader;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowSlip(Reader reader, Book book, LocalDate borrowDate, LocalDate returnDate) {
        this.reader = reader;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
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

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public void showSlip() {
        System.out.println("---Phieu muon sach---");
        System.out.println("Doc gia: " + reader.getFullName() + " (" + reader.getReaderID() + ")");
        System.out.println("Sach: " + book.getName() + " (" + book.getBookID() + ")");
        System.out.println("Ngay muon: " + borrowDate);
        System.out.println("Tinh trang: " + (returnDate != null ? "Da tra" : "Chua tra"));
    }
}
