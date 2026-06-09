package model;

public class BorrowResult {
    private final boolean success;
    private final String message;

    public BorrowResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
