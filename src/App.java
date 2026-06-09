import model.Student;
import model.Lecturer;
import model.SeniorReader;
import service.Library;
import policy.CharityFeePolicy;
import policy.WaivedFeePolicy;

public class App {
    public static void main(String[] args) {
        Library library = new Library();
        library.addReader(new Student("SV01", "Nguyen Van A", "SINH_VIEN"));
        library.addReader(new Lecturer("GV01", "Tran Thi B", "GIANG_VIEN"));
        library.addReader(new SeniorReader("CC01", "Le Van C", "CC2024"));

        // Thang binh thuong:
        System.out.println("=== Thang binh thuong ===");
        library.calculateTotalFees(7);

        // Thang tu thien - chi doi policy, khong sua code Library
        library.setFeePolicy(new CharityFeePolicy());
        System.out.println("\n=== Thang tu thien ===");
        library.calculateTotalFees(7);

        // Thang khai truong - mien phi
        library.setFeePolicy(new WaivedFeePolicy());
        System.out.println("\n=== Thang khai truong ===");
        library.calculateTotalFees(7);
    }
}
