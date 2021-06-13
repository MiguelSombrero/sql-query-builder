package builder.query.create.table.foreignkey;

import builder.query.SQLQueryBuilder;
import builder.query.create.table.column.Constraint;
import builder.query.create.table.column.DataType;
import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

public class ForeignKeyValidationTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;

    @Before
    public void setUp() {
        initializeDatabase();

        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableForeignKeyWithSQLInjection() {
        sqlQueryBuilder.create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey().autoIncrement()
                .column("person_id").type(DataType.INT).unique().notNull()
                .foreignKey(";DROP").references("ID", "person")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableForeignKeyReferenceWithSQLInjection() {
        sqlQueryBuilder.create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey().autoIncrement()
                .column("person_id").type(DataType.INT).unique().notNull()
                .foreignKey("person_id").references(";DROP", "person")
                .build();
    }
}
