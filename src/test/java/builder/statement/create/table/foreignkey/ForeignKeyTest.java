package builder.statement.create.table.foreignkey;

import builder.statement.create.table.column.Constraint;
import builder.statement.create.table.column.DataType;
import database.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static factory.QueryFactory.create;
import static junit.framework.Assert.assertEquals;

public class ForeignKeyTest extends DatabaseTestBaseClass {
    private Constraint column;

    @Before
    public void setUp() {
        initializeDatabase();

        this.column = create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey()
                .column("person_id").type(DataType.INT)
                .column("manufacturer_id").type(DataType.INT);;
    }

    @Test
    public void testCreateTableConstraintForeignKey() throws SQLException {
        String query = this.column
                .foreignKey("person_id")
                    .references("ID", "person")
                .build();

        logger.info(query);

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID))", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateTableConstraintForeignKeyOnActionCascade() throws SQLException {
        String query = this.column
                .foreignKey("person_id")
                    .references("ID", "person")
                    .onDelete().cascade()
                .foreignKey("manufacturer_id")
                    .references("ID", "manufacturer")
                    .onUpdate().cascade()
                .build();

        logger.info(query);

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID) ON DELETE CASCADE, FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID) ON UPDATE CASCADE)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateTableConstraintForeignKeyOnActionRestrict() throws SQLException {
        String query = this.column
                .foreignKey("person_id")
                    .references("ID", "person")
                    .onDelete().restrict()
                .foreignKey("manufacturer_id")
                    .references("ID", "manufacturer")
                    .onUpdate().restrict()
                .build();

        logger.info(query);

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID) ON DELETE RESTRICT, FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID) ON UPDATE RESTRICT)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateTableConstraintForeignKeyOnActionSetNull() throws SQLException {
        String query = this.column
                .foreignKey("person_id")
                    .references("ID", "person")
                    .onDelete().setNull()
                .foreignKey("manufacturer_id")
                    .references("ID", "manufacturer")
                    .onUpdate().setNull()
                .build();

        logger.info(query);

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID) ON DELETE SET NULL, FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID) ON UPDATE SET NULL)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testCreateTableConstraintForeignKeyOnActionSetDefault() throws SQLException {
        String query = this.column
                .foreignKey("person_id")
                    .references("ID", "person")
                    .onDelete().setDefault()
                .foreignKey("manufacturer_id")
                    .references("ID", "manufacturer")
                    .onUpdate().setDefault()
                .build();

        logger.info(query);

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID) ON DELETE SET DEFAULT, FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID) ON UPDATE SET DEFAULT)", query);
        assertThatQueryIsValidSQL(query);
    }
}
