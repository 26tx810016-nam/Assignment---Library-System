public class Reader {
    private String readerID;
    private String fullName;
    private String email;
    private ReaderType type;

    // Theo dõi số lượng sách đã mượn
    private int borrowedCount = 0;

    public Reader(String readerID, String fullName, String email, ReaderType type) {
        this.readerID = readerID;
        this.fullName = fullName;
        this.email = email;
        this.type = type;
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

    public ReaderType getType() {
        return type;
    }

    public void setType(ReaderType type) {
        this.type = type;
    }


    // Kiểm tra số lượng sách đã mượn và kiểm tra xem có mượn được nữa không
    public int getBorrowedCount() {
        return borrowedCount;
    }

    public int getRemainingBorrowAllowance() {
        if (type == null) return 0;
        return Math.max(0, type.getBorrowLimit() - borrowedCount);
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

    public void showInfo() {
        System.out.println("Doc gia ID: " + readerID);
        System.out.println("Ho va ten: " + fullName);
        System.out.println("Email: " + email);
        System.out.println("Loai doc gia: " + ReaderType.nameOf(type));
    }
}
