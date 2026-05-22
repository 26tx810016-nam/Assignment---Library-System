import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books = new ArrayList<>();
    private final List<Reader> readers = new ArrayList<>();
    private final List<BorrowSlip> borrowSlips = new ArrayList<>();

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
}
