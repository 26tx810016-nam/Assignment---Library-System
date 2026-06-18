package model;

import java.util.ArrayList;
import java.util.List;
import service.Notifiable;

public abstract class Reader implements Notifiable {
    protected String readerID;
    protected String fullName;
    protected String email;

    // Theo dõi số lượng sách đã mượn
    private int borrowedCount = 0;
    
    // Theo dõi lịch sử thông báo
    private final List<String> notificationHistory;

    protected Reader(String readerID, String fullName, String email) {
        this.readerID = readerID;
        this.fullName = fullName;
        this.email = email;
        this.notificationHistory = new ArrayList<>();
    }

    @Override
    public void sendNotification(String message) {
        if (message != null && !message.isEmpty()) {
            notificationHistory.add(message);
            System.out.println("[" + readerID + "] " + message);
        }
    }

    @Override
    public List<String> getNotificationHistory() {
        return new ArrayList<>(notificationHistory);
    }

    public abstract int getMaxBorrow();

    public abstract int calculateLateFee(int daysLate);

    public abstract String getInfo();

    protected abstract boolean checkSpecialCondition(Book book);

    protected abstract String getSpecialConditionMessage();


    public final BorrowResult processBorrow(Book book) {
        if (!checkBorrowQuota()) {
            return new BorrowResult(false, "Da dat gioi han muon: " + getMaxBorrow() + " cuon");
        }
        if (!checkSpecialCondition(book)) {
            return new BorrowResult(false, getSpecialConditionMessage());
        }
        book.decreaseQuantity();
        borrowedCount++;
        onBorrowSuccess(book);
        return new BorrowResult(true, "Muon thanh cong: " + book.getName());
    }

    private boolean checkBorrowQuota() {
        return borrowedCount < getMaxBorrow();
    }

    protected void onBorrowSuccess(Book book) {
        System.out.println(getFullName() + " muon: " + book.getName());
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
}
