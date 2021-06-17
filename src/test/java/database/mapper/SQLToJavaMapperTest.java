package database.mapper;

import database.column.*;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.Test;
import testutils.DatabaseConnection;

import java.sql.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class SQLToJavaMapperTest {
    private static SQLToJavaMapper mapper;

    @Test
    public void testThatCharMapsToString() throws SQLException {
        int type = Types.CHAR;
        Object value = "";
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
    public void testThatDateMapsToLocalDate() throws SQLException {
        int type = Types.DATE;
        Object value = new Date(2020);
        assertThat(mapper.toJavaType(type, value), instanceOf(DateColumnValue.class));
    }

    @Test
    public void testThatTimestampMapsToLocalDateTime() throws SQLException {
        int type = Types.TIMESTAMP;
        Object value = Timestamp.valueOf("2020-02-02 21:00:00");
        assertThat(mapper.toJavaType(type, value), instanceOf(DateTimeColumnValue.class));
    }

    @Test
    public void testThatBlobMapsToByteArray() throws SQLException {
        int type = Types.BLOB;

        Connection connection = DatabaseConnection.getConnection();

        Object value = connection.createBlob();

        assertThat(mapper.toJavaType(type, value), instanceOf(ByteArrayColumnValue.class));

        connection.close();
    }

    @Test(expected = NotImplementedException.class)
    public void testThatNotImplementedThrowsException() throws SQLException {
        int type = Types.DECIMAL;
        Object value = "";
        mapper.toJavaType(type, value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatUnknownTypeThrowsException() throws SQLException {
        int type = 2576897;
        Object value = "";
        mapper.toJavaType(type, value);
    }
}
