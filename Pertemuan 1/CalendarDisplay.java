import java.time.LocalDate;

public class CalendarDisplay {
    public String getTodayDate() { // mereturn tanggal saat ini sebagai string
        LocalDate today = LocalDate.now();
        return today.toString();
    }
}
