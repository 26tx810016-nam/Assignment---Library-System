package model;

public class Lecturer extends Reader {
    
    public Lecturer(String readerID, String fullName, String email) {
        super(readerID, fullName, email);
    }

    @Override
    public int getMaxBorrow() {
        return 5;
    }

    @Override
    public int calculateLateFee(int daysLate) {
        return daysLate * 1000;
    }

    @Override
    protected boolean checkSpecialCondition(Book book) {
        return true;
    }

    @Override
    protected String getSpecialConditionMessage() {
        return "Giang vien khong co dieu kien dac biet.";
    }

    @Override
    public String getInfo() {
        return "[GV] " + getReaderID() + " | " + getFullName()
               + " | Email: " + getEmail()
               + " | Han muon: " + getMaxBorrow() + " cuon";
    }
}
