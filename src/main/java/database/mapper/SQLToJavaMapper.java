package database.mapper;

import database.column.*;
import database.converter.SQLToJavaConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;

public class SQLToJavaMapper {
    protected static Logger logger = LoggerFactory.getLogger(SQLToJavaMapper.class);

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
            case Types.BOOLEAN, Types.TINYINT, Types.BIT:
                return new BooleanColumnValue((Boolean) value);
            case Types.DATE:
                return new DateColumnValue((Date) value);
            case Types.TIMESTAMP:
                return new TimestampColumnValue((Timestamp) value);
            case Types.BLOB:
                return new ByteArrayColumnValue(convert.blobToByteArray((Blob) value));
            case Types.BINARY, Types.VARBINARY, Types.LONGVARBINARY:
                return new ByteArrayColumnValue((byte[]) value);
            case Types.DECIMAL, Types.NUMERIC:
                return new BigDecimalColumnValue((BigDecimal) value);
            case Types.CLOB, Types.NCLOB:
                throw new UnsupportedOperationException("Should be mapped as byte array");
            case Types.SMALLINT:
                throw new UnsupportedOperationException("SmallInt should be mapped as short");
            default:
                throw new IllegalArgumentException("Could not map SQL type to any Java type");
        }
    }
}
