public class Student extends Reader {
    
    public Student(String readerID, String fullName, String email) {
        super(readerID, fullName, email);
    }

    @Override
    public int getMaxBorrow() {
        return 3;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLoai the: Sinh vien";
    }
}
