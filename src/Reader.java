public class Reader {
    private String readerID;
    private String fullName;
    private String email;

    // Theo dõi số lượng sách đã mượn
    private int borrowedCount = 0;

    public Reader(String readerID, String fullName, String email) {
        this.readerID = readerID;
        this.fullName = fullName;
        this.email = email;
    }

    public String getReaderID() {
        return readerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMaxBorrow() {
        return 0;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public int getRemainingBorrowAllowance() {
        return Math.max(0, getMaxBorrow() - borrowedCount);
    }

    public boolean canBorrow() {
        return getRemainingBorrowAllowance() > 0;
    }

    public void borrowOne() {
        if (canBorrow()) {
            borrowedCount++;
        }
    }

    public void returnOne() {
        if (borrowedCount > 0) {
            borrowedCount--;
        }
    }

    @Override
    public String toString() {
        return "Doc gia ID: " + readerID + 
               "\nHo va ten: " + fullName + 
               "\nEmail: " + email;
    }

    public void showInfo() {
        System.out.println(toString());
    }
}
