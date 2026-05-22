import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class Fine {
    public static final int DAILY_FINE = 5000;

    private Fine() {}

    public static int calculateFine(LocalDate dueDate, LocalDate returnDate) {
        long lateDays = ChronoUnit.DAYS.between(dueDate, returnDate);

        return (int) (lateDays * DAILY_FINE);
    }
}
