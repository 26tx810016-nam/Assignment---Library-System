package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import service.Fineable;

public final class Fine implements Fineable {
    public static final int DAILY_FINE = 5000;
    private int fineAmount;
    private boolean isPaid;
    private LocalDate dueDate;

    public Fine() {
        this.fineAmount = 0;
        this.isPaid = false;
        this.dueDate = null;
    }

    public Fine(int fineAmount) {
        this.fineAmount = fineAmount;
        this.isPaid = false;
        this.dueDate = LocalDate.now().plusDays(7); // Default: 7 days to pay
    }

    @Override
    public int calculateFinePerDay() {
        return DAILY_FINE;
    }

    @Override
    public int getFineAmount(int lateDays) {
        if (lateDays <= 0) {
            return 0;
        }
        return lateDays * DAILY_FINE;
    }

    @Override
    public String getFineStatus() {
        if (fineAmount == 0) {
            return "Không có tiền phạt";
        }
        if (isPaid) {
            return "Đã thanh toán";
        }
        if (LocalDate.now().isAfter(dueDate)) {
            return "Quá hạn thanh toán";
        }
        return "Chưa thanh toán";
    }

    @Override
    public boolean isFinePaymentDue() {
        return !isPaid && fineAmount > 0 && LocalDate.now().isAfter(dueDate);
    }

    public int getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(int amount) {
        if (Fineable.isValidFineAmount(amount)) {
            this.fineAmount = amount;
        }
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void payFine() {
        if (fineAmount > 0) {
            this.isPaid = true;
        }
    }

    public static int calculateFine(LocalDate dueDate, LocalDate returnDate) {
        long lateDays = ChronoUnit.DAYS.between(dueDate, returnDate);
        return (int) (lateDays * DAILY_FINE);
    }
}
