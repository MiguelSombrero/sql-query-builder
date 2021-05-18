package builder.statement.create.table.column;

import database.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static factory.QueryFactory.create;
import static junit.framework.Assert.assertEquals;

public class CreateTableTest extends DatabaseTestBaseClass {

    @Before
    public void setUp() {
        initializeDatabase();
    }

    @Test
    public void testCreateTableDataTypes() throws SQLException {
        String query = create()
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

        assertEquals("CREATE TABLE cars (ID INT, age DOUBLE, created TIMESTAMP, country CHAR, model VARCHAR(32), brand VARCHAR(64), disclaimer VARCHAR(128), description VARCHAR(255))", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateTableConstraintNotNull() throws SQLException {
        String query = create()
                .table("planes")
                .column("ID").type(DataType.INT).notNull()
                .column("age").type(DataType.DOUBLE)
                .column("created").type(DataType.TIMESTAMP).notNull()
                .build();

        logger.info(query);

        assertEquals("CREATE TABLE planes (ID INT NOT NULL, age DOUBLE, created TIMESTAMP NOT NULL)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateTableConstraintUnique() throws SQLException {
        String query = create()
                .table("bikes")
                .column("ID").type(DataType.INT).unique()
                .column("age").type(DataType.DOUBLE)
                .column("created").type(DataType.TIMESTAMP).unique()
                .build();

        logger.info(query);

        assertEquals("CREATE TABLE bikes (ID INT UNIQUE, age DOUBLE, created TIMESTAMP UNIQUE)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateTableConstraintPrimaryKey() throws SQLException {
        String query = create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey()
                .column("age").type(DataType.DOUBLE)
                .build();

        logger.info(query);

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, age DOUBLE)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateTableConstraintAutoIncrement() throws SQLException {
        String query = create()
                .table("vehicles")
                .column("ID").type(DataType.INT).autoIncrement()
                .column("person_id").type(DataType.INT)
                .foreignKey("person_id").references("ID", "person")
                .build();

        logger.info(query);

        assertEquals("CREATE TABLE vehicles (ID INT AUTO_INCREMENT, person_id INT, FOREIGN KEY (person_id) REFERENCES person(ID))", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateTableConstraintMultipleConstraint() throws SQLException {
        String query = create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey().autoIncrement()
                .column("person_id").type(DataType.INT).unique().notNull()
                .foreignKey("person_id").references("ID", "person")
                .build();

        logger.info(query);

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY AUTO_INCREMENT, person_id INT UNIQUE NOT NULL, FOREIGN KEY (person_id) REFERENCES person(ID))", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableWithSQLInjection() {
        create()
                .table(";DROP")
                .column("ID").type(DataType.INT).primaryKey().autoIncrement()
                .column("person_id").type(DataType.INT).unique().notNull()
                .foreignKey("person_id").references("ID", "person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableFirstColumnWithSQLInjection() {
        create()
                .table("vehicles")
                .column("--DROP").type(DataType.INT).primaryKey().autoIncrement()
                .column("person_id").type(DataType.INT).unique().notNull()
                .foreignKey("person_id").references("ID", "person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableColumnWithSQLInjection() {
        create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey().autoIncrement()
                .column(";DROP").type(DataType.INT).unique().notNull()
                .foreignKey("person_id").references("ID", "person")
                .build();
    }

}
