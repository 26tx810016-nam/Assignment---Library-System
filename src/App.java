import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian("L001", "Ms. Lan");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Lua chon: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addBook(scanner, library);
                    break;
                case "2":
                    addReader(scanner, library);
                    break;
                case "3":
                    borrowBook(scanner, library, librarian);
                    break;
                case "4":
                    returnBook(scanner, library, librarian);
                    break;
                case "5":
                    searchBooks(scanner, library);
                    break;
                case "6":
                    showOverdueSlips(scanner, library);
                    break;
                case "7":
                    library.showLibraryInfo();
                    break;
                case "8":
                    System.out.println("Thoat chuong trinh.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lua chon khong hop le, vui long thu lai.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("===== MENU THU VIEN =====");
        System.out.println("1. Them sach");
        System.out.println("2. Them doc gia");
        System.out.println("3. Muon sach");
        System.out.println("4. Tra sach");
        System.out.println("5. Tim sach theo ten/tac gia");
        System.out.println("6. Danh sach phieu muon qua han");
        System.out.println("7. Thong tin thu vien");
        System.out.println("8. Thoat");
    }

    private static void addBook(Scanner scanner, Library library) {
        System.out.print("Nhap ma sach: ");
        String bookID = scanner.nextLine().trim();
        System.out.print("Nhap ten sach: ");
        String name = scanner.nextLine().trim();
        System.out.print("Nhap tac gia: ");
        String author = scanner.nextLine().trim();
        System.out.print("Nhap nam xuat ban: ");
        int publishDate = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nhap so luong: ");
        int quantity = Integer.parseInt(scanner.nextLine().trim());

        Book book = new Book(bookID, name, author, publishDate, quantity);
        library.addBook(book);
        System.out.println("Them sach thanh cong.");
    }

    private static void addReader(Scanner scanner, Library library) {
        System.out.print("Nhap ma doc gia: ");
        String readerID = scanner.nextLine().trim();
        System.out.print("Nhap ten doc gia: ");
        String fullName = scanner.nextLine().trim();
        System.out.print("Nhap email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Chon loai doc gia (1-SINH_VIEN, 2-GIANG_VIEN): ");
        String typeChoice = scanner.nextLine().trim();

        ReaderType type = "2" .equals(typeChoice) ? ReaderType.GIANG_VIEN : ReaderType.SINH_VIEN;
        Reader reader = new Reader(readerID, fullName, email, type);
        library.addReader(reader);
        System.out.println("Them doc gia thanh cong.");
    }

    private static void borrowBook(Scanner scanner, Library library, Librarian librarian) {
        System.out.print("Nhap ma doc gia: ");
        String readerID = scanner.nextLine().trim();
        System.out.print("Nhap ma sach: ");
        String bookID = scanner.nextLine().trim();

        Reader reader = library.findReaderByID(readerID);
        Book book = library.findBookByID(bookID);

        if (reader == null) {
            System.out.println("Khong tim thay doc gia.");
            return;
        }
        if (book == null) {
            System.out.println("Khong tim thay sach.");
            return;
        }

        LocalDate borrowDate = readDate(scanner, "Nhap ngay muon (yyyy-mm-dd): ");
        LocalDate dueDate = readDate(scanner, "Nhap ngay tra du kien (yyyy-mm-dd): ");
        if (borrowDate == null || dueDate == null) {
            System.out.println("Ngay khong hop le.");
            return;
        }

        BorrowSlip slip = librarian.lendBook(reader, book, borrowDate, dueDate);
        if (slip != null) {
            library.addBorrowSlip(slip);
        }
    }

    private static void returnBook(Scanner scanner, Library library, Librarian librarian) {
        System.out.print("Nhap ma doc gia: ");
        String readerID = scanner.nextLine().trim();
        System.out.print("Nhap ma sach: ");
        String bookID = scanner.nextLine().trim();

        BorrowSlip slip = library.findActiveBorrowSlip(readerID, bookID);
        if (slip == null) {
            System.out.println("Khong tim thay phieu muon dang hoat dong.");
            return;
        }

        LocalDate returnDate = readDate(scanner, "Nhap ngay tra thuc te (yyyy-mm-dd): ");
        if (returnDate == null) {
            System.out.println("Ngay khong hop le.");
            return;
        }

        librarian.receiveBook(slip, returnDate);
    }

    private static void searchBooks(Scanner scanner, Library library) {
        System.out.print("Nhap tu khoa tim kiem: ");
        String query = scanner.nextLine().trim();
        Book result = library.findBookByNameOrAuthor(query);

        if (result == null) {
            System.out.println("Khong tim thay sach phu hop.");
            return;
        }

        System.out.println("Ket qua tim kiem:");
        result.showInfo();
        System.out.println();
    }

    private static void showOverdueSlips(Scanner scanner, Library library) {
        LocalDate referenceDate = readDate(scanner, "Nhap ngay tham chieu de kiem tra qua han (yyyy-mm-dd): ");
        if (referenceDate == null) {
            System.out.println("Ngay khong hop le.");
            return;
        }

        List<BorrowSlip> overdue = library.findOverdueSlips(referenceDate);
        if (overdue.isEmpty()) {
            System.out.println("Khong co phieu muon nao qua han.");
            return;
        }

        System.out.println("Danh sach phieu muon qua han:");
        for (BorrowSlip slip : overdue) {
            slip.showSlip();
            System.out.println();
        }
    }

    private static LocalDate readDate(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            return LocalDate.parse(scanner.nextLine().trim());
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
