package database.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class SQLToJavaConverter {
    private static Logger logger = LoggerFactory.getLogger(SQLToJavaConverter.class);

    private static final ZoneId TIMEZONE = ZoneId.systemDefault();

    public static LocalDate dateToLocalDate(Date date) {
        LocalDate result = date.toLocalDate();
        return result;
    }

    public static LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        LocalDateTime result = timestamp.toInstant()
                .atZone(TIMEZONE)
                .toLocalDateTime();

        return result;
    }

    public static byte[] blobToByteArray(Blob blob) throws SQLException {
        byte[] blobAsBytes;

        try {
            int blobLength = (int) blob.length();
            blobAsBytes = blob.getBytes(1, blobLength);
        } catch (SQLException e) {
            logger.info("Converting Blob to byte array failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        } finally {
            blob.free();
        }

        return blobAsBytes;
    }
}
