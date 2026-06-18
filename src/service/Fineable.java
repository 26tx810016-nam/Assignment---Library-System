package service;

public interface Fineable {
    // Abstract methods
    int calculateFinePerDay();
    int getFineAmount(int lateDays);
    String getFineStatus();
    boolean isFinePaymentDue();

    // Default method - calculate total fine for multiple days of violation
    default int calculateTotalFine(int lateDays) {
        if (lateDays <= 0) {
            return 0;
        }
        return getFineAmount(lateDays);
    }

    // Static method - check if fine amount is valid
    static boolean isValidFineAmount(int amount) {
        return amount >= 0 && amount <= 10000000; // Valid range: 0 to 10,000,000 VND
    }
}

