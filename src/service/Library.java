package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.Reader;
import model.BorrowSlip;
import policy.LateFeePolicy;
import policy.StandardFeePolicy;

public class Library {
    private final List<Book> books;
    private final List<Reader> readers;
    private final List<BorrowSlip> borrowSlips;
    private LateFeePolicy feePolicy;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.borrowSlips = new ArrayList<>();
        this.feePolicy = new StandardFeePolicy();
    }

    public void setFeePolicy(LateFeePolicy policy) {
        this.feePolicy = policy;
        System.out.println("Cap nhat chinh sach phi phat: " + policy.getPolicyName());
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public void addBorrowSlip(BorrowSlip slip) {
        borrowSlips.add(slip);
    }

    public Reader findReaderByID(String readerID) {
        for (Reader reader : readers) {
            if (reader.getReaderID().equalsIgnoreCase(readerID)) {
                return reader;
            }
        }
        return null;
    }

    // Tìm sách theo ID
    public Book findBookByID(String bookID) {
        for (Book book : books) {
            if (book.getBookID().equalsIgnoreCase(bookID)) {
                return book;
            }
        }
        return null;
    }

    // Tìm sách theo tên hoặc tác giả
    public Book findBookByNameOrAuthor(String search) {
        String lower = search.toLowerCase();

        for (Book book : books) {
            if (book.getName().toLowerCase().contains(lower)
                    || book.getAuthor().toLowerCase().contains(lower)) {
                return book;
            }
        }
        return null;
    }

    // Tìm phiếu mượn còn hạn
    public BorrowSlip findActiveBorrowSlip(String readerID, String bookID) {
        for (BorrowSlip slip : borrowSlips) {
            if (!slip.isReturned()
                    && slip.getReader().getReaderID().equalsIgnoreCase(readerID)
                    && slip.getBook().getBookID().equalsIgnoreCase(bookID)) {
                return slip;
            }
        }
        return null;
    }
    
    // Tìm các phiếu mượn quá hạn dựa trên ngày hiện tại
    public List<BorrowSlip> findOverdueSlips(LocalDate currentDate) {
        List<BorrowSlip> result = new ArrayList<>();

        for (BorrowSlip slip : borrowSlips) {
            if (!slip.isReturned()) {
                LocalDate dueDate = slip.getReturnDate();
                if (dueDate.isBefore(currentDate)) {
                    result.add(slip);
                }
            }
        }
        return result;
    }

    public void showLibraryInfo() {
        System.out.println("=== Library Information ===");
        System.out.println("Books: " + books.size());
        System.out.println("Readers: " + readers.size());
        System.out.println("Borrow slips: " + borrowSlips.size());
    }

    public void calculateTotalFees(int daysLate) {
        System.out.println("=== PHI PHAT TRE HAN (" + daysLate + " ngay) ===");
        double total = 0;
        for (Reader r : readers) {
            double baseFee = r.calculateLateFee(daysLate);
            double adjustedFee = feePolicy.applyPolicy(baseFee);
            System.out.printf("%-25s | Base: %,.0f | Sau CS: %,.0f VND%n",
                    r.getFullName(), baseFee, adjustedFee);
            total += adjustedFee;
        }
        System.out.printf("Tong phi phat (%s): %,.0f VND%n", feePolicy.getPolicyName(), total);
    }

    public void showAllReaders() {
        System.out.println("==== DANH SACH DOC GIA ====");
        for (Reader r : readers) {
            System.out.println(r.getInfo());
        }
    }

    public void showAllBooks() {
        System.out.println("==== DANH SACH SACH ====");
        for (Book book : books) {
            book.showInfo();
            System.out.println();
        }
    }
}
