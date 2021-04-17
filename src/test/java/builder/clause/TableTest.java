package builder.clause;

import builder.field.Field;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TableTest {
    private Field field;

    @Before
    public void setUp() {
        this.field = QueryFactory
                .select()
                .field("*");
    }

    @Test
    public void testFromOneTable() {
        String query = this.field
                .from("persons")
                .build();

        assertEquals("SELECT * FROM persons;", query);
    }

    @Test
    public void testFromMultipleTables() {
        String query = this.field
                .from("persons")
                .and("addresses")
                .and("houses")
                .build();

        assertEquals("SELECT * FROM persons, addresses, houses;", query);
    }

    @Test
    public void testFromMultipleTablesWithAliases() {
        String query = this.field
                .from("persons").alias("p")
                .and("addresses").alias("a")
                .and("houses").alias("h")
                .build();

        assertEquals("SELECT * FROM persons AS p, addresses AS a, houses AS h;", query);
    }

    @Test
    public void testInnerJoin() {
        String query = this.field
                .from("persons")
                .innerJoin("addresses").on("persons.id = addresses.person_id")
                .build();

        assertEquals("SELECT * FROM persons INNER JOIN addresses ON persons.id = addresses.person_id;", query);
    }

    @Test
    public void testLeftJoin() {
        String query = this.field
                .from("persons")
                .leftJoin("addresses").on("persons.id = addresses.person_id")
                .build();

        assertEquals("SELECT * FROM persons LEFT JOIN addresses ON persons.id = addresses.person_id;", query);
    }

    @Test
    public void testMultipleJoins() {
        String query = this.field
                .from("persons")
                .leftJoin("addresses").on("persons.id = addresses.person_id")
                .innerJoin("accounts").on("persons.id = accounts.person_id")
                .build();

        assertEquals("SELECT * FROM persons LEFT JOIN addresses ON persons.id = addresses.person_id INNER JOIN accounts ON persons.id = accounts.person_id;", query);
    }
}
