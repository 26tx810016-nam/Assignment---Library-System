public class Lecturer extends Reader {
    
    public Lecturer(String readerID, String fullName, String email) {
        super(readerID, fullName, email);
    }

    @Override
    public int getMaxBorrow() {
        return 5;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLoai the: Giang vien";
    }
}
