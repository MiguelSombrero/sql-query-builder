package database.mapper;

import database.column.*;
import org.apache.commons.lang3.NotImplementedException;
import utils.DateConverter;

import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;

public class SQLTypeToJavaTypeMapper {

    public static ColumnValue toJavaType(int type, Object value) {
        switch (type) {
            case Types.CHAR, Types.NCHAR, Types.LONGNVARCHAR, Types.LONGVARCHAR, Types.VARCHAR, Types.NVARCHAR:
                return new StringColumnValue((String) value);
            case Types.INTEGER:
                return new IntegerColumnValue((Integer) value);
            case Types.BIGINT:
                return new LongColumnValue((Long) value);
            case Types.DOUBLE, Types.FLOAT:
                return new DoubleColumnValue((Double) value);
            case Types.BOOLEAN:
                return new BooleanColumnValue((Boolean) value);
            case Types.DATE:
                return new DateColumnValue(DateConverter.dateToLocalDate((Date) value));
            case Types.TIMESTAMP:
                return new DateTimeColumnValue(DateConverter.timestampToLocalDateTime((Timestamp) value));
            case Types.BLOB, Types.CLOB, Types.NCLOB:
                return new ByteArrayColumnValue(getBytes(value));
            case Types.DECIMAL, Types.NUMERIC:
                throw new NotImplementedException("Should be mapped as BigDecimal");
            case Types.SMALLINT:
                throw new NotImplementedException("SmallInt should be mapped as short");
            default:
                throw new IllegalArgumentException("Could not map SQL type to Java type");
        }
    }

    private static byte[] getBytes(Object value) {
        String stringValue = String.valueOf(value);
        return stringValue.getBytes(StandardCharsets.UTF_8);
    }
}
