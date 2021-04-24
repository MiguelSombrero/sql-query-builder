package builder.insert;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InsertTest extends DatabaseTestBaseClass {
    private FirstColumn column;

    @Before
    public void setUpQuery() {
        this.column = QueryFactory
                .inserInto()
                .table("person");
    }

    @Test
    public void testLastIndexOfRightBracket() {
        assertEquals(39, column.column("firstname").lastIndexOfRightBracket());
    }

    @Test
    public void testFirstIndexOfRightBracket() {
        assertEquals(20, column.firstIndexOfRightBracket());
    }

    @Test
    public void testInsertOneValue() {
        String query = QueryFactory
                .inserInto()
                .table("person")
                .column("id").value(1)
                .build();

        logger.info(query);

        assertEquals("INSERT INTO person (id) VALUES (1);", query);
    }

    @Test
    public void testInsertTwoValues() {
        String query = QueryFactory
                .inserInto()
                .table("person")
                .column("id").value(1)
                .column("birthdate").value("1980-04-12")
                .build();

        logger.info(query);

        assertEquals("INSERT INTO person (id, birthdate) VALUES (1, '1980-04-12');", query);
    }
}
