package builder.create;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class CreateTest extends DatabaseTestBaseClass {

    @Test
    public void testDataTypes() throws SQLException {
        String query = QueryFactory
                .create()
                .table("cars")
                .column("ID").type(DataType.INT)
                .column("age").type(DataType.DOUBLE)
                .column("created").type(DataType.TIMESTAMP)
                .column("country").type(DataType.CHAR)
                .column("model").type(DataType.VARCHAR_32)
                .column("brand").type(DataType.VARCHAR_64)
                .column("disclaimer").type(DataType.VARCHAR_128)
                .column("description").type(DataType.VARCHAR_255)
                .build();

        logger.info(query);

        assertEquals("CREATE TABLE cars (ID INT, age DOUBLE, created TIMESTAMP, country CHAR, model VARCHAR(32), brand VARCHAR(64), disclaimer VARCHAR(128), description VARCHAR(255));", query);
        assertThatQueryIsValidSQL(query);
    }

}
