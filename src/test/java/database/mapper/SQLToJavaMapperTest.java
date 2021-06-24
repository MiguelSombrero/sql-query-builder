package database.mapper;

import database.column.*;
import org.junit.Test;
import testutils.DatabaseConnection;

import java.nio.charset.StandardCharsets;
import java.sql.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class SQLToJavaMapperTest {
    private static SQLToJavaMapper mapper;

    @Test
    public void testThatCharMapsToString() throws SQLException {
        int type = Types.CHAR;
        Object value = "a";
        assertThat(mapper.toJavaType(type, value), instanceOf(StringColumnValue.class));
    }

    @Test
    public void testThatNCharMapsToString() throws SQLException {
        int type = Types.NCHAR;
        Object value = "";
        assertThat(mapper.toJavaType(type, value), instanceOf(StringColumnValue.class));
    }

    @Test
    public void testThatLONGNVARCHARMapsToString() throws SQLException {
        int type = Types.LONGNVARCHAR;
        Object value = "";
        assertThat(mapper.toJavaType(type, value), instanceOf(StringColumnValue.class));
    }

    @Test
    public void testThatLONGVARCHARMapsToString() throws SQLException {
        int type = Types.LONGVARCHAR;
        Object value = "";
        assertThat(mapper.toJavaType(type, value), instanceOf(StringColumnValue.class));
    }

    @Test
    public void testThatNVARCHARMapsToString() throws SQLException {
        int type = Types.NVARCHAR;
        Object value = "";
        assertThat(mapper.toJavaType(type, value), instanceOf(StringColumnValue.class));
    }

    @Test
    public void testThatVARCHARMapsToString() throws SQLException {
        int type = Types.VARCHAR;
        Object value = "";
        assertThat(mapper.toJavaType(type, value), instanceOf(StringColumnValue.class));
    }

    @Test
    public void testThatIntegerMapsToInteger() throws SQLException {
        int type = Types.INTEGER;
        Object value = 1;
        assertThat(mapper.toJavaType(type, value), instanceOf(IntegerColumnValue.class));
    }

    @Test
    public void testThatSmallIntMapsToShort() throws SQLException {
        int type = Types.SMALLINT;
        Object value = Short.valueOf("1");
        assertThat(mapper.toJavaType(type, value), instanceOf(ShortColumnValue.class));
    }

    @Test
    public void testThatBigIntMapsToLong() throws SQLException {
        int type = Types.BIGINT;
        Object value = 12453452L;
        assertThat(mapper.toJavaType(type, value), instanceOf(LongColumnValue.class));
    }

    @Test
    public void testThatDoubleMapsToDouble() throws SQLException {
        int type = Types.DOUBLE;
        Object value = 124.12;
        assertThat(mapper.toJavaType(type, value), instanceOf(DoubleColumnValue.class));
    }

    @Test
    public void testThatFloatMapsToDouble() throws SQLException {
        int type = Types.FLOAT;
        Object value = 124.12;
        assertThat(mapper.toJavaType(type, value), instanceOf(DoubleColumnValue.class));
    }

    @Test
    public void testThatBooleanMapsToBoolean() throws SQLException {
        int type = Types.BOOLEAN;
        Object value = true;
        assertThat(mapper.toJavaType(type, value), instanceOf(BooleanColumnValue.class));
    }

    @Test
    public void testThatDateMapsToDate() throws SQLException {
        int type = Types.DATE;
        Object value = new Date(2020);
        assertThat(mapper.toJavaType(type, value), instanceOf(DateColumnValue.class));
    }

    @Test
    public void testThatTimestampMapsToTimestamp() throws SQLException {
        int type = Types.TIMESTAMP;
        Object value = Timestamp.valueOf("2020-02-02 21:00:00");
        assertThat(mapper.toJavaType(type, value), instanceOf(TimestampColumnValue.class));
    }

    @Test
    public void testThatBlobMapsToByteArray() throws SQLException {
        int type = Types.BLOB;

        Connection connection = DatabaseConnection.getConnection();

        Object value = connection.createBlob();

        assertThat(mapper.toJavaType(type, value), instanceOf(ByteArrayColumnValue.class));

        connection.close();
    }

    @Test
    public void testThatBinaryMapsToByteArray() throws SQLException {
        int type = Types.BINARY;
        Object value = "test String".getBytes(StandardCharsets.UTF_8);
        assertThat(mapper.toJavaType(type, value), instanceOf(ByteArrayColumnValue.class));
    }

    @Test
    public void testThatVarbinaryMapsToByteArray() throws SQLException {
        int type = Types.VARBINARY;
        Object value = "test String".getBytes(StandardCharsets.UTF_8);
        assertThat(mapper.toJavaType(type, value), instanceOf(ByteArrayColumnValue.class));
    }

    @Test
    public void testThatLongvarBinaryMapsToByteArray() throws SQLException {
        int type = Types.LONGVARBINARY;
        Object value = "test String".getBytes(StandardCharsets.UTF_8);
        assertThat(mapper.toJavaType(type, value), instanceOf(ByteArrayColumnValue.class));
    }

    /*@Test
    public void testThatDecimalMapsToBigDecimal() throws SQLException {
        int type = Types.DECIMAL;
        Object value = 1234567.89;
        assertThat(mapper.toJavaType(type, value), instanceOf(BigDecimalColumnValue.class));
    }

    @Test
    public void testThatNumericMapsToBigDecimal() throws SQLException {
        int type = Types.NUMERIC;
        Object value = 1234567.89;
        assertThat(mapper.toJavaType(type, value), instanceOf(BigDecimalColumnValue.class));
    }
*/
    @Test(expected = IllegalArgumentException.class)
    public void testThatUnknownTypeThrowsException() throws SQLException {
        int type = 2576897;
        Object value = "";
        mapper.toJavaType(type, value);
    }
}
