package builder.clause;

import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FromTest {
    private Value value;

    @Before
    public void setUp() {
        this.value = QueryFactory
                .select()
                .value("*");
    }

    @Test
    public void testFromOneTable() {
        String query = this.value
                .from("persons")
                .build();

        assertEquals("SELECT * FROM persons;", query);
    }

    @Test
    public void testFromMultipleTables() {
        String query = this.value
                .from("persons")
                .from("addresses")
                .from("houses")
                .build();

        assertEquals("SELECT * FROM persons, addresses, houses;", query);
    }

    @Test
    public void testFromMultipleTablesWithAliases() {
        String query = this.value
                .from("persons").alias("p")
                .from("addresses").alias("a")
                .from("houses").alias("h")
                .build();

        assertEquals("SELECT * FROM persons AS p, addresses AS a, houses AS h;", query);
    }

    @Test
    public void testInnerJoin() {
        String query = this.value
                .from("persons")
                .innerJoin("addresses")
                .build();

        assertEquals("SELECT * FROM persons INNER JOIN addresses;", query);
    }
}
