package builder.statement.create;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Test;

import java.sql.SQLException;

import static factory.QueryFactory.create;
import static junit.framework.Assert.assertEquals;

public class CreateTest extends DatabaseTestBaseClass {

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
    public void testCreateTableConstraintForeignKey() throws SQLException {
        String query = create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey()
                .column("person_id").type(DataType.INT)
                .foreignKey("person_id").references("ID", "person")
                .build();

        logger.info(query);

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, FOREIGN KEY (person_id) REFERENCES person(ID))", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateTableConstraintMultipleForeignKey() throws SQLException {
        String query = create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey()
                .column("person_id").type(DataType.INT)
                .column("manufacturer_id").type(DataType.INT)
                .foreignKey("person_id").references("ID", "person")
                .foreignKey("manufacturer_id").references("ID", "manufacturer")
                .build();

        logger.info(query);

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID), FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID))", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateDatabase() throws SQLException {
        String query = create()
                .database("test_db")
                .build();

        assertEquals("CREATE DATABASE test_db", query);
        // command not supported in H2?
        // assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateIndex() throws SQLException {
        String query = create()
                .index("person_index")
                .on("person")
                .columns("id", "firstname", "lastname")
                .build();

        assertEquals("CREATE INDEX person_index ON person (id, firstname, lastname)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateTableConstraintMultipleForeignKeyss() throws SQLException {
        String query = create()
                .table("vehicles")
                    .column("ID").type(DataType.INT).primaryKey()
                    .column("name").type(DataType.VARCHAR_255).notNull()
                    .column("model").type(DataType.VARCHAR_64)
                    .column("manufacturer_id").type(DataType.INT)
                .foreignKey("manufacturer_id").references("ID", "manufacturer")
                .build();

        logger.info(query);

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID), FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID))", query);
        assertThatQueryIsValidSQL(query);
    }
}
