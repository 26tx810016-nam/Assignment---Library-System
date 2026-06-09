package model;

public class SeniorReader extends Reader {
    
    public SeniorReader(String readerID, String fullName, String email) {
        super(readerID, fullName, email);
    }

    @Override
    public int getMaxBorrow() {
        return 10;
    }

    @Override
    public int calculateLateFee(int daysLate) {
        return 0;
    }

    @Override
    protected boolean checkSpecialCondition(Book book) {
        return true;
    }

    @Override
    protected String getSpecialConditionMessage() {
        return "";
    }

    @Override
    public String getInfo() {
        return "[CC] " + getReaderID() + " | " + getFullName()
               + " | Email: " + getEmail()
               + " | Han muon: " + getMaxBorrow() + " cuon";
    }
}
