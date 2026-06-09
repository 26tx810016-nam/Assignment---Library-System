package model;

public class Student extends Reader {
    
    public Student(String readerID, String fullName, String email) {
        super(readerID, fullName, email);
    }

    @Override
    public int getMaxBorrow() {
        return 3;
    }

    @Override
    public int calculateLateFee(int daysLate) {
        return daysLate * 2000;
    }

    @Override
    protected boolean checkSpecialCondition(Book book) {
        return !book.isReferenceOnly();
    }

    @Override
    protected String getSpecialConditionMessage() {
        return "Sach tham khao chi doc tai cho - sinh vien khong duoc mang ve";
    }

    @Override
    public String getInfo() {
        return "[SV] " + getReaderID() + " | " + getFullName()
               + " | Email: " + getEmail()
               + " | Han muon: " + getMaxBorrow() + " cuon";
    }
}
