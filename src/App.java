public class App {
    public static void main(String[] args) {
        Library library = new Library();
        
        // Create and add Books
        System.out.println("===== TAO SACH =====");
        Book book1 = new Book("B001", "Java Programming", "Hoang Duc", 2020, 5);
        Book book2 = new Book("B002", "Data Structures", "Nguyen Van A", 2019, 3);
        Book book3 = new Book("B003", "Database Design", "Tran Thi B", 2021, 4);
        
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        
        book1.showInfo();
        System.out.println();
        book2.showInfo();
        System.out.println();
        book3.showInfo();
        System.out.println();
        
        // Create and add Students
        System.out.println("===== TAO SINH VIEN =====");
        Student student1 = new Student("SV001", "Nguyen Van A", "vana@email.com");
        Student student2 = new Student("SV002", "Tran Thi B", "thib@email.com");
        
        library.addReader(student1);
        library.addReader(student2);
        
        System.out.println(student1.toString());
        System.out.println("Max borrow: " + student1.getMaxBorrow());
        System.out.println();
        System.out.println(student2.toString());
        System.out.println("Max borrow: " + student2.getMaxBorrow());
        System.out.println();
        
        // Create and add Lecturers
        System.out.println("===== TAO GIANG VIEN =====");
        Lecturer lecturer1 = new Lecturer("GV001", "Hoang Minh", "hoangminh@email.com");
        Lecturer lecturer2 = new Lecturer("GV002", "Le Van C", "lovanc@email.com");
        
        library.addReader(lecturer1);
        library.addReader(lecturer2);
        
        System.out.println(lecturer1.toString());
        System.out.println("Max borrow: " + lecturer1.getMaxBorrow());
        System.out.println();
        System.out.println(lecturer2.toString());
        System.out.println("Max borrow: " + lecturer2.getMaxBorrow());
        System.out.println();
        
        // Show library info
        System.out.println("===== THONG TIN THU VIEN =====");
        library.showLibraryInfo();
        System.out.println();
        

        System.out.println("===== KIEM CHUNG KE THUA VA POLYMORPHISM =====");
        System.out.println("Danh sach tat ca doc gia:");
        System.out.println();
        
        // Reader array chua duoc ca Student va Lecturer
        Reader[] allReaders = {student1, student2, lecturer1, lecturer2};
        for (Reader reader : allReaders) {
            System.out.println(reader.toString());
            System.out.println( "Max borrow: " + reader.getMaxBorrow() + 
                                " | Can borrow: " + reader.canBorrow());
            System.out.println();
        }
    }
}
