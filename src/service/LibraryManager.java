package service;
import java.util.List;

public class LibraryManager {
    
    // Duyet item trong danh sach Borrowable va in thong tin
    public void processAllBorrowable(List<Borrowable> items) {
        System.out.println("\n=== DANH SACH MUON SACH ===");
        if (items == null || items.isEmpty()) {
            System.out.println("Danh sach trong.");
            return;
        }
        
        for (int i = 0; i < items.size(); i++) {
            Borrowable item = items.get(i);
            System.out.println("\n[" + (i + 1) + "] " + item.getClass().getSimpleName());
            System.out.println("   - Trang thai: " + (item.isAvailable() ? "Co san" : "Da muon"));
            System.out.println("   - ID nguoi muon: " + item.getBorrowerId());
            System.out.println("   - Tien phat 5 ngay: " + item.calculateFine(5) + " VND");
        }
    }
    
    // Gui thong bao den tat ca doc gia
    public void notifyAll(List<Notifiable> users, String message) {
        System.out.println("\n=== GUI THONG BAO DEN TAT CA DOC GIA ===");
        if (users == null || users.isEmpty()) {
            System.out.println("Danh sach doc gia trong.");
            return;
        }
        
        System.out.println("Noi dung: " + message);
        for (Notifiable user : users) {
            user.sendNotification(message);
        }
    }
}
