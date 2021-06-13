package database.mapper;

import database.column.*;
import org.apache.commons.lang3.NotImplementedException;
import database.converter.SQLToJavaConverter;

import java.sql.*;

public class SQLToJavaMapper {

    private static SQLToJavaConverter convert;

    public static ColumnValue toJavaType(int type, Object value) throws SQLException {
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
                return new DateColumnValue(convert.dateToLocalDate((Date) value));
            case Types.TIMESTAMP:
                return new DateTimeColumnValue(convert.timestampToLocalDateTime((Timestamp) value));
            case Types.BLOB:
                return new ByteArrayColumnValue(convert.blobToByteArray((Blob) value));
            case Types.CLOB, Types.NCLOB:
                throw new NotImplementedException("Should be mapped as byte array");
            case Types.DECIMAL, Types.NUMERIC:
                throw new NotImplementedException("Should be mapped as BigDecimal");
            case Types.SMALLINT:
                throw new NotImplementedException("SmallInt should be mapped as short");
            default:
                throw new IllegalArgumentException("Could not map SQL type to any Java type");
        }
    }
}
