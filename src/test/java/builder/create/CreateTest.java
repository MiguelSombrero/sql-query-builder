package builder.create;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class CreateTest extends DatabaseTestBaseClass {

    @Test
    public void testField() throws SQLException {
        String query = QueryFactory
                .create()
                .table("cars")
                .column("age").type(DataType.INT)
                .build();

        assertEquals("CREATE TABLE cars (age INT);", query);
        assertThatQueryIsValidSQL(query);
    }

}
