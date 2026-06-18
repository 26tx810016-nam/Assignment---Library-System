package service;

import java.util.List;

public interface Notifiable {
    // Abstract methods
    void sendNotification(String message);
    List<String> getNotificationHistory();

    // Default method - send overdue notification with default message
    default void sendOverdueNotification() {
        sendNotification("[THONG BAO] Ban co sach bi tre han.");
    }
}
