package database.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Blob;
import java.sql.SQLException;

public class SQLToJavaConverter {
    private static Logger logger = LoggerFactory.getLogger(SQLToJavaConverter.class);

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
