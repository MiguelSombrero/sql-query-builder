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
        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());
    }

    @Test
    public void testCreateTableDataTypes() throws SQLException {
        CreateQuery query = sqlQueryBuilder.create()
                .table("cars")
                .column("ID").type(DataType.INT)
                .column("hash").type(DataType.BIGINT)
                .column("age").type(DataType.DOUBLE)
                .column("date").type(DataType.DATE)
                .column("created").type(DataType.TIMESTAMP)
                .column("active").type(DataType.BOOLEAN)
                .column("country").type(DataType.CHAR_32)
                //TODO: rest of the CHAR types
                .column("model").type(DataType.VARCHAR_32)
                .column("brand").type(DataType.VARCHAR_64)
                .column("disclaimer").type(DataType.VARCHAR_128)
                .column("description").type(DataType.VARCHAR_255)
                .column("contract").type(DataType.BLOB)
                .build();

        assertEquals("CREATE TABLE cars (ID INT, hash BIGINT, age DOUBLE, date DATE, created TIMESTAMP, active BOOLEAN, country CHAR(32), model VARCHAR(32), brand VARCHAR(64), disclaimer VARCHAR(128), description VARCHAR(255), contract BLOB)", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM cars", 0);
    }

    @Test
    public void testCreateTableConstraintNotNull() throws SQLException {
        CreateQuery query = sqlQueryBuilder.create()
                .table("planes")
                .column("ID").type(DataType.INT).notNull()
                .column("age").type(DataType.DOUBLE)
                .column("created").type(DataType.TIMESTAMP).notNull()
                .build();

        assertEquals("CREATE TABLE planes (ID INT NOT NULL, age DOUBLE, created TIMESTAMP NOT NULL)", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM planes", 0);
    }

    @Test
    public void testCreateTableConstraintUnique() throws SQLException {
        CreateQuery query = sqlQueryBuilder.create()
                .table("bikes")
                .column("ID").type(DataType.INT).unique()
                .column("age").type(DataType.DOUBLE)
                .column("created").type(DataType.TIMESTAMP).unique()
                .build();

        assertEquals("CREATE TABLE bikes (ID INT UNIQUE, age DOUBLE, created TIMESTAMP UNIQUE)", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM bikes", 0);
    }

    @Test
    public void testCreateTableConstraintPrimaryKey() throws SQLException {
        CreateQuery query = sqlQueryBuilder.create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey()
                .column("age").type(DataType.DOUBLE)
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, age DOUBLE)", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM vehicles", 0);
    }

    @Test
    public void testCreateTableConstraintAutoIncrement() throws SQLException {
        CreateQuery query = sqlQueryBuilder.create()
                .table("vehicles")
                .column("ID").type(DataType.INT).autoIncrement()
                .column("person_id").type(DataType.INT)
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
                .column("ID").type(DataType.INT).primaryKey().autoIncrement()
                .column("person_id").type(DataType.INT).unique().notNull()
                .foreignKey("person_id").references("ID", "person")
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY AUTO_INCREMENT, person_id INT UNIQUE NOT NULL, FOREIGN KEY (person_id) REFERENCES person(ID))", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM vehicles", 0);
    }
}
