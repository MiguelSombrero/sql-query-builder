package builder.statement.create.table.column;

import query.dql.Row;
import query.QueryFactory;
import query.ddl.DDLQuery;
import query.dql.DQLQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

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
        DDLQuery query = queryFactory.create()
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

        query.execute();

        /*DMLQuery insert = queryFactory
                .insertInto()
                .table("cars")
                .values()
                    .value(105)
                    .value(12)
                    .value("2020-02-02")
                    .value("Finland")
                    .value("ADC12")
                    .value("Opel")
                    .value("Nodisclaimers")
                    .value("Nodescriptions")
                .build();

        insert.execute();*/

        DQLQuery select = queryFactory
                .select()
                    .all()
                .from()
                    .table("cars")
                .build();

        List<Row> selectResults = select.execute();

        //assertEquals("Opel", selectResults.get(0).getStringFrom(5));
        //assertEquals(1, result);
    }

    @Test
    public void testCreateTableConstraintNotNull() throws SQLException {
        DDLQuery query = queryFactory.create()
                .table("planes")
                .column("ID").type(DataType.INT).notNull()
                .column("age").type(DataType.DOUBLE)
                .column("created").type(DataType.TIMESTAMP).notNull()
                .build();

        assertEquals("CREATE TABLE planes (ID INT NOT NULL, age DOUBLE, created TIMESTAMP NOT NULL)", query.toString());

        query.execute();
    }

    @Test
    public void testCreateTableConstraintUnique() throws SQLException {
        DDLQuery query = queryFactory.create()
                .table("bikes")
                .column("ID").type(DataType.INT).unique()
                .column("age").type(DataType.DOUBLE)
                .column("created").type(DataType.TIMESTAMP).unique()
                .build();

        assertEquals("CREATE TABLE bikes (ID INT UNIQUE, age DOUBLE, created TIMESTAMP UNIQUE)", query.toString());

        query.execute();
    }

    @Test
    public void testCreateTableConstraintPrimaryKey() throws SQLException {
        DDLQuery query = queryFactory.create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey()
                .column("age").type(DataType.DOUBLE)
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY, age DOUBLE)", query.toString());

        query.execute();
    }

    @Test
    public void testCreateTableConstraintAutoIncrement() throws SQLException {
        DDLQuery query = queryFactory.create()
                .table("vehicles")
                .column("ID").type(DataType.INT).autoIncrement()
                .column("person_id").type(DataType.INT)
                .foreignKey("person_id").references("ID", "person")
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT AUTO_INCREMENT, person_id INT, FOREIGN KEY (person_id) REFERENCES person(ID))", query.toString());

        query.execute();
    }

    @Test
    public void testCreateTableConstraintMultipleConstraint() throws SQLException {
        DDLQuery query = queryFactory.create()
                .table("vehicles")
                .column("ID").type(DataType.INT).primaryKey().autoIncrement()
                .column("person_id").type(DataType.INT).unique().notNull()
                .foreignKey("person_id").references("ID", "person")
                .build();

        assertEquals("CREATE TABLE vehicles (ID INT PRIMARY KEY AUTO_INCREMENT, person_id INT UNIQUE NOT NULL, FOREIGN KEY (person_id) REFERENCES person(ID))", query.toString());

        query.execute();
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
