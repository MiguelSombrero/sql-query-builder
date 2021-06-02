package builder.statement.create.table.foreignkey;

import builder.statement.create.table.column.Constraint;
import builder.statement.create.table.column.DataType;
import query.QueryFactory;
import query.Query;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ForeignKeyTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;
    private Constraint column;

    @Before
    public void setUp() {
        initializeDatabase();

        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());

        this.column = queryFactory.create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey()
                .column("person_id").type(DataType.INT)
                .column("manufacturer_id").type(DataType.INT);;
    }

    @Test
    public void testCreateTableConstraintForeignKey() throws SQLException {
        Query query = this.column
                .foreignKey("person_id")
                    .references("ID", "person")
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID))", query.toString());
    }

    @Test
    public void testCreateTableConstraintForeignKeyOnActionCascade() throws SQLException {
        Query query = this.column
                .foreignKey("person_id")
                    .references("ID", "person")
                    .onDelete().cascade()
                .foreignKey("manufacturer_id")
                    .references("ID", "manufacturer")
                    .onUpdate().cascade()
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID) ON DELETE CASCADE, FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID) ON UPDATE CASCADE)", query.toString());
    }

    @Test
    public void testCreateTableConstraintForeignKeyOnActionRestrict() throws SQLException {
        Query query = this.column
                .foreignKey("person_id")
                    .references("ID", "person")
                    .onDelete().restrict()
                .foreignKey("manufacturer_id")
                    .references("ID", "manufacturer")
                    .onUpdate().restrict()
                .build();


        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID) ON DELETE RESTRICT, FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID) ON UPDATE RESTRICT)", query.toString());
    }

    @Test
    public void testCreateTableConstraintForeignKeyOnActionSetNull() throws SQLException {
        Query query = this.column
                .foreignKey("person_id")
                    .references("ID", "person")
                    .onDelete().setNull()
                .foreignKey("manufacturer_id")
                    .references("ID", "manufacturer")
                    .onUpdate().setNull()
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID) ON DELETE SET NULL, FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID) ON UPDATE SET NULL)", query.toString());
    }

    @Test
    public void testCreateTableConstraintForeignKeyOnActionSetDefault() throws SQLException {
        Query query = this.column
                .foreignKey("person_id")
                    .references("ID", "person")
                    .onDelete().setDefault()
                .foreignKey("manufacturer_id")
                    .references("ID", "manufacturer")
                    .onUpdate().setDefault()
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, person_id INT, manufacturer_id INT, FOREIGN KEY (person_id) REFERENCES person(ID) ON DELETE SET DEFAULT, FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID) ON UPDATE SET DEFAULT)", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableForeignKeyWithSQLInjection() {
        queryFactory.create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey().autoIncrement()
                .column("person_id").type(DataType.INT).unique().notNull()
                .foreignKey(";DROP").references("ID", "person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableForeignKeyReferenceWithSQLInjection() {
        queryFactory.create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey().autoIncrement()
                .column("person_id").type(DataType.INT).unique().notNull()
                .foreignKey("person_id").references(";DROP", "person")
                .build();
    }
}
