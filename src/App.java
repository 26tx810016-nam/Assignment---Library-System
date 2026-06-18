import model.Student;
import model.Book;
import model.Lecturer;
import model.SeniorReader;
import model.BorrowSlip;
import service.Borrowable;
import service.Notifiable;
import service.LibraryManager;
import service.Library;
import policy.CharityFeePolicy;
import policy.WaivedFeePolicy;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("====== TEST BORROWABLE INTERFACE ======\n");
        
        Borrowable book1 = new Book("B001", "Clean Code", "Robert Martin"); 
        Borrowable book2 = new Book("B002", "Design Patterns", "GoF"); 

        book1.borrowBy("R001", "2024-09-01"); 
        System.out.println("Available: " + book2.isAvailable()); // true 

        // Dung static method cua interface 
        System.out.println(Borrowable.isValidBorrowDuration(10)); // true 
        System.out.println(Borrowable.isValidBorrowDuration(20)); // false 

        // Dung default method 
        System.out.println(book1.calculateFine(3)); // 15000.0 
        book1.returnBook("2024-09-15");
        
        
        System.out.println("\n====== TEST LIBRARYMANAGER METHODS ======\n");
        
        // Khởi tạo LibraryManager
        LibraryManager manager = new LibraryManager();
        
        // Tạo danh sách sách
        Book book3 = new Book("B003", "Effective Java", "Joshua Bloch");
        Book book4 = new Book("B004", "Refactoring", "Martin Fowler");
        Book book5 = new Book("B005", "Algorithms", "Cormen");
        
        // Tạo danh sách độc giả
        Student student = new Student("S001", "Nguyen Duc Huy", "huy@student.edu");
        Lecturer lecturer = new Lecturer("L001", "Dr. Tran Minh Duc", "duc@university.edu");
        SeniorReader senior = new SeniorReader("SR001", "Ong Le Van Tuan", "tuan@email.com");
        
        // Tạo BorrowSlips
        BorrowSlip slip1 = new BorrowSlip(student, book3);
        slip1.borrowBy("S001", "2024-09-01");
        
        BorrowSlip slip2 = new BorrowSlip(lecturer, book4);
        slip2.borrowBy("L001", "2024-09-05");
        
        BorrowSlip slip3 = new BorrowSlip(senior, book5);
        slip3.borrowBy("SR001", "2024-09-10");
        
        // TEST 1: processAllBorrowable() - Danh sách hỗn hợp Borrowable
        System.out.println("--- TEST 1: processAllBorrowable() ---");
        List<Borrowable> borrowableList = new ArrayList<>();
        borrowableList.add(book3);
        borrowableList.add(book4);
        borrowableList.add(slip1);
        borrowableList.add(slip2);
        borrowableList.add(slip3);
        
        manager.processAllBorrowable(borrowableList);
        
        // TEST 2: notifyAll() - Gửi thông báo tới tất cả độc giả
        System.out.println("\n--- TEST 2: notifyAll() ---");
        List<Notifiable> notifiableList = new ArrayList<>();
        notifiableList.add(student);
        notifiableList.add(lecturer);
        notifiableList.add(senior);
        
        manager.notifyAll(notifiableList, 
            "THONG BAO: Vui long kiem tra va thanh toan tien phat neu co sach tre han!");
        
    }
}
