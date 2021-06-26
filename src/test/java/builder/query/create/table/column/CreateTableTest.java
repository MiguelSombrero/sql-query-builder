package builder.query.create.table.column;

import builder.query.SQLQueryBuilder;
import query.CreateQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CreateTableTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getH2DataSource());
    }

    @Test
    public void testCreateTableDataTypes() throws SQLException {
        CreateQuery query = sqlQueryBuilder.create()
                .table("cars")
                .column("ID").type("INT")
                .column("hash").type("BIGINT")
                .column("age").type("DOUBLE")
                .column("price").type("DECIMAL(8,2)")
                .column("taxes").type("NUMERIC(4,2)")
                .column("date").type("DATE")
                .column("created").type("TIMESTAMP")
                .column("active").type("BOOLEAN")
                .column("country").type("CHAR(255)")
                .column("model").type("VARCHAR(32)")
                .column("brand").type("VARCHAR(64)")
                .column("description").type("VARCHAR(255)")
                .column("contract").type("BLOB")
                .build();

        assertEquals("CREATE TABLE cars (ID INT, hash BIGINT, age DOUBLE, price DECIMAL(8,2), taxes NUMERIC(4,2), date DATE, created TIMESTAMP, active BOOLEAN, country CHAR(255), model VARCHAR(32), brand VARCHAR(64), description VARCHAR(255), contract BLOB)", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM cars", 0);
    }

    @Test
    public void testCreateTableConstraintNotNull() throws SQLException {
        CreateQuery query = sqlQueryBuilder.create()
                .table("planes")
                .column("ID").type("INT").notNull()
                .column("age").type("DOUBLE")
                .column("created").type("TIMESTAMP").notNull()
                .build();

        assertEquals("CREATE TABLE planes (ID INT NOT NULL, age DOUBLE, created TIMESTAMP NOT NULL)", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM planes", 0);
    }

    @Test
    public void testCreateTableConstraintUnique() throws SQLException {
        CreateQuery query = sqlQueryBuilder.create()
                .table("bikes")
                .column("ID").type("INT").unique()
                .column("age").type("DOUBLE")
                .column("created").type("TIMESTAMP").unique()
                .build();

        assertEquals("CREATE TABLE bikes (ID INT UNIQUE, age DOUBLE, created TIMESTAMP UNIQUE)", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM bikes", 0);
    }

    @Test
    public void testCreateTableConstraintPrimaryKey() throws SQLException {
        CreateQuery query = sqlQueryBuilder.create()
                .table("vehicles")
                .column("ID").type("INT").primaryKey()
                .column("age").type("DOUBLE")
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, age DOUBLE)", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM vehicles", 0);
    }

    @Test
    public void testCreateTableConstraintAutoIncrement() throws SQLException {
        CreateQuery query = sqlQueryBuilder.create()
                .table("vehicles")
                .column("ID").type("INT").autoIncrement()
                .column("person_id").type("INT")
                .foreignKey("person_id").references("ID", "person")
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT AUTO_INCREMENT, person_id INT, FOREIGN KEY (person_id) REFERENCES person(ID))", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM vehicles", 0);
    }

    @Test
    public void testCreateTableConstraintMultipleConstraint() throws SQLException {
        CreateQuery query = sqlQueryBuilder.create()
                .table("vehicles")
                .column("ID").type("INT").primaryKey().autoIncrement()
                .column("person_id").type("INT").unique().notNull()
                .foreignKey("person_id").references("ID", "person")
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY AUTO_INCREMENT, person_id INT UNIQUE NOT NULL, FOREIGN KEY (person_id) REFERENCES person(ID))", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM vehicles", 0);
    }
}
