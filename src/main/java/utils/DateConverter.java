package utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateConverter {

    public static LocalDate dateToLocalDate(Date date) {
        LocalDate result = date.toLocalDate();
        return result;
    }

    public static LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        LocalDateTime result = timestamp
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return result;
    }
}
