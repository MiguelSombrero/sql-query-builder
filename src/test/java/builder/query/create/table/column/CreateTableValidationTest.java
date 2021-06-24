package builder.query.create.table.column;

import builder.query.SQLQueryBuilder;
import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import java.sql.SQLException;

public class CreateTableValidationTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableWithSQLInjection() {
        sqlQueryBuilder.create()
                .table(";DROP")
                .column("ID").type("INT").primaryKey().autoIncrement()
                .column("person_id").type("INT").unique().notNull()
                .foreignKey("person_id").references("ID", "person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableFirstColumnWithSQLInjection() {
        sqlQueryBuilder.create()
                .table("vehicles")
                .column("--DROP").type("INT").primaryKey().autoIncrement()
                .column("person_id").type("INT").unique().notNull()
                .foreignKey("person_id").references("ID", "person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableColumnWithSQLInjection() {
        sqlQueryBuilder.create()
                .table("vehicles")
                .column("ID").type("INT").primaryKey().autoIncrement()
                .column(";DROP").type("INT").unique().notNull()
                .foreignKey("person_id").references("ID", "person")
                .build();
    }

}
