package builder.statement.create.table.column;

import query.QueryFactory;
import query.Query;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CreateTableTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;

    @Before
    public void setUp() {
        initializeDatabase();
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());
    }

    @Test
    public void testCreateTableDataTypes() throws SQLException {
        Query query = queryFactory.create()
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

        assertEquals("CREATE TABLE cars (ID INT, age DOUBLE, created TIMESTAMP, country CHAR, model VARCHAR(32), brand VARCHAR(64), disclaimer VARCHAR(128), description VARCHAR(255))", query.toString());
    }

    @Test
    public void testCreateTableConstraintNotNull() throws SQLException {
        Query query = queryFactory.create()
                .table("planes")
                .column("ID").type(DataType.INT).notNull()
                .column("age").type(DataType.DOUBLE)
                .column("created").type(DataType.TIMESTAMP).notNull()
                .build();

        assertEquals("CREATE TABLE planes (ID INT NOT NULL, age DOUBLE, created TIMESTAMP NOT NULL)", query.toString());
    }

    @Test
    public void testCreateTableConstraintUnique() throws SQLException {
        Query query = queryFactory.create()
                .table("bikes")
                .column("ID").type(DataType.INT).unique()
                .column("age").type(DataType.DOUBLE)
                .column("created").type(DataType.TIMESTAMP).unique()
                .build();

        assertEquals("CREATE TABLE bikes (ID INT UNIQUE, age DOUBLE, created TIMESTAMP UNIQUE)", query.toString());
    }

    @Test
    public void testCreateTableConstraintPrimaryKey() throws SQLException {
        Query query = queryFactory.create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey()
                .column("age").type(DataType.DOUBLE)
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, age DOUBLE)", query.toString());
    }

    @Test
    public void testCreateTableConstraintAutoIncrement() throws SQLException {
        Query query = queryFactory.create()
                .table("vehicles")
                .column("ID").type(DataType.INT).autoIncrement()
                .column("person_id").type(DataType.INT)
                .foreignKey("person_id").references("ID", "person")
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT AUTO_INCREMENT, person_id INT, FOREIGN KEY (person_id) REFERENCES person(ID))", query.toString());
    }

    @Test
    public void testCreateTableConstraintMultipleConstraint() throws SQLException {
        Query query = queryFactory.create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey().autoIncrement()
                .column("person_id").type(DataType.INT).unique().notNull()
                .foreignKey("person_id").references("ID", "person")
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY AUTO_INCREMENT, person_id INT UNIQUE NOT NULL, FOREIGN KEY (person_id) REFERENCES person(ID))", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableWithSQLInjection() {
        queryFactory.create()
                .table(";DROP")
                .column("ID").type(DataType.INT).primaryKey().autoIncrement()
                .column("person_id").type(DataType.INT).unique().notNull()
                .foreignKey("person_id").references("ID", "person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableFirstColumnWithSQLInjection() {
        queryFactory.create()
                .table("vehicles")
                .column("--DROP").type(DataType.INT).primaryKey().autoIncrement()
                .column("person_id").type(DataType.INT).unique().notNull()
                .foreignKey("person_id").references("ID", "person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableColumnWithSQLInjection() {
        queryFactory.create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey().autoIncrement()
                .column(";DROP").type(DataType.INT).unique().notNull()
                .foreignKey("person_id").references("ID", "person")
                .build();
    }

}
