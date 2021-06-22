package builder.query.create.table.foreignkey;

import builder.query.SQLQueryBuilder;
import builder.query.create.table.column.Constraint;
import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import query.CreateQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ForeignKeyTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;
    private Constraint baseQuery;

    @Before
    public void setUp() {
        initializeDatabase();

        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());

        this.baseQuery = sqlQueryBuilder.create()
                .table("vehicles")
                .column("ID").type("INT").primaryKey()
                .column("person_id").type("INT")
                .column("manufacturer_id").type("INT");
    }

    @Test(expected = JdbcSQLSyntaxErrorException.class)
    public void testSelectNonExitingTableThrowsSQLException() throws SQLException {
        assertThatQueryReturnsRows("SELECT * FROM nonexistring", 0);
    }

    @Test
    public void testCreateTableConstraintForeignKey() throws SQLException {
        CreateQuery query = this.baseQuery
                .foreignKey("person_id")
                    .references("ID", "person")
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID))", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM vehicles", 0);
    }

    @Test
    public void testCreateTableConstraintForeignKeyOnActionCascade() throws SQLException {
        CreateQuery query = this.baseQuery
                .foreignKey("person_id")
                    .references("ID", "person")
                    .onDelete().cascade()
                .foreignKey("manufacturer_id")
                    .references("ID", "manufacturer")
                    .onUpdate().cascade()
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID) ON DELETE CASCADE, FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID) ON UPDATE CASCADE)", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM vehicles", 0);
    }

    @Test
    public void testCreateTableConstraintForeignKeyOnActionRestrict() throws SQLException {
        CreateQuery query = this.baseQuery
                .foreignKey("person_id")
                    .references("ID", "person")
                    .onDelete().restrict()
                .foreignKey("manufacturer_id")
                    .references("ID", "manufacturer")
                    .onUpdate().restrict()
                .build();


        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID) ON DELETE RESTRICT, FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID) ON UPDATE RESTRICT)", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM vehicles", 0);
    }

    @Test
    public void testCreateTableConstraintForeignKeyOnActionSetNull() throws SQLException {
        CreateQuery query = this.baseQuery
                .foreignKey("person_id")
                    .references("ID", "person")
                    .onDelete().setNull()
                .foreignKey("manufacturer_id")
                    .references("ID", "manufacturer")
                    .onUpdate().setNull()
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID) ON DELETE SET NULL, FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID) ON UPDATE SET NULL)", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM vehicles", 0);
    }

    @Test
    public void testCreateTableConstraintForeignKeyOnActionSetDefault() throws SQLException {
        CreateQuery query = this.baseQuery
                .foreignKey("person_id")
                    .references("ID", "person")
                    .onDelete().setDefault()
                .foreignKey("manufacturer_id")
                    .references("ID", "manufacturer")
                    .onUpdate().setDefault()
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID) ON DELETE SET DEFAULT, FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID) ON UPDATE SET DEFAULT)", query.toString());

        query.execute();
        assertThatQueryReturnsRows("SELECT * FROM vehicles", 0);
    }
}
