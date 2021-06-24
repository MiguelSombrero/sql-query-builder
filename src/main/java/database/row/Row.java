package database.row;

import database.column.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

public class Row {
    protected static Logger logger = LoggerFactory.getLogger(Row.class);

    private Map<String, ColumnValue> columns;

    public Row(Map<String, ColumnValue> columns) {
        this.columns = columns;
    }

    public int getColumnCount() {
        return this.columns.size();
    }

    /**
     * Get String value from column of this row. SQL types
     * CHAR, NCHAR, LONGNVARCHAR, LONGVARCHAR, VARCHAR and
     * NVARCHAR maps to String. Use this method for fetching
     * these values from row.
     *
     * @param columnName Column name where value is fetched. If
     * column name is aliased, use alias name instead
     *
     * @return String value from column
     */
    public String getString(String columnName) {
        StringColumnValue value = (StringColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    /**
     * Get int value from column of this row. SQL type
     * INTEGER maps to String. Use this method for fetching
     * these values from row.
     *
     * @param columnName Column name where value is fetched. If
     * column name is aliased, use alias name instead
     *
     * @return Integer value from column
     */
    public int getInteger(String columnName) {
        IntegerColumnValue value = (IntegerColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    /**
     * Get short value from column of this row. SQL type
     * SMALLINT maps to Short. Use this method for fetching
     * these values from row.
     *
     * @param columnName Column name where value is fetched. If
     * column name is aliased, use alias name instead
     *
     * @return Short value from column
     */
    public short getShort(String columnName) {
        ShortColumnValue value = (ShortColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    /**
     * Get long value from column of this row. SQL type
     * BIGINT maps to Long. Use this method for fetching
     * these values from row.
     *
     * @param columnName Column name where value is fetched. If
     * column name is aliased, use alias name instead
     *
     * @return Long value from column
     */
    public long getLong(String columnName) {
        LongColumnValue value = (LongColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    /**
     * Get double value from column of this row. SQL types
     * FLOAT and DOUBLE maps to Double. Use this method for fetching
     * these values from row.
     *
     * @param columnName Column name where value is fetched. If
     * column name is aliased, use alias name instead
     *
     * @return Double value from column
     */
    public double getDouble(String columnName) {
        DoubleColumnValue value = (DoubleColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    /**
     * Get BigDecimal value from column of this row. SQL types
     * DECIMAL and NUMERIC maps to BigDecimal. Use this method for fetching
     * these values from row.
     *
     * @param columnName Column name where value is fetched. If
     * column name is aliased, use alias name instead
     *
     * @return BigDecimal value from column
     */
    public BigDecimal getBigDecimal(String columnName) {
        BigDecimalColumnValue value = (BigDecimalColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    /**
     * Get value as byte array from column of this row. SQL types
     * BLOB, BINARY, VARBINARY and LONGVARBINARY maps to BigDecimal.
     * Use this method for fetching these values from row.
     *
     * @param columnName Column name where value is fetched. If
     * column name is aliased, use alias name instead
     *
     * @return Byte array value from column
     */
    public byte[] getBytes(String columnName) {
        ByteArrayColumnValue value = (ByteArrayColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    /**
     * Get boolean value from column of this row. SQL types
     * BOOLEAN, TINYINT and BIT maps to BigDecimal.
     * Use this method for fetching these values from row.
     *
     * @param columnName Column name where value is fetched. If
     * column name is aliased, use alias name instead
     *
     * @return boolean value from column
     */
    public boolean getBoolean(String columnName) {
        BooleanColumnValue value = (BooleanColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    /**
     * Get Date value from column of this row. SQL type
     * DATE maps to java.sq.Date. Use this method for fetching
     * these values from row.
     *
     * @param columnName Column name where value is fetched. If
     * column name is aliased, use alias name instead
     *
     * @return java.sql.Date value from column
     */
    public Date getDate(String columnName) {
        DateColumnValue value = (DateColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    /**
     * Get Timestamp value from column of this row. SQL type
     * TIMESTAMP maps to java.sq.Timestamp. Use this method for fetching
     * these values from row.
     *
     * @param columnName Column name where value is fetched. If
     * column name is aliased, use alias name instead
     *
     * @return java.sql.Timestamp value from column
     */
    public Timestamp getTimestamp(String columnName) {
        TimestampColumnValue value = (TimestampColumnValue) getColumnValue(columnName);
        return value.getValue();
    }

    private ColumnValue getColumnValue(String columnName) {
        ColumnValue value;

        try {
            value = this.columns.get(columnName.toLowerCase());
        } catch (NullPointerException e) {
            logger.info("Did not find column with column name " + columnName);
            logger.debug(e.getLocalizedMessage());
            throw e;
        }

        return value;
    }
}
