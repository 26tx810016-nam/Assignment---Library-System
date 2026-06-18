package service;

import java.time.LocalDate;

public interface Returnable {
    void confirmReturn(String date);
    String getReturnDate();
    boolean isReturned();

    default boolean isLate(String dueDate) {
        if (dueDate == null) {
            return false;
        }
        return LocalDate.now().isAfter(LocalDate.parse(dueDate));
    }
}
