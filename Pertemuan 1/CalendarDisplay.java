import java.time.LocalDate;

public class CalendarDisplay {
    public String getTodayDate() {
        LocalDate today = LocalDate.now();
        return today.toString();
    }
}
