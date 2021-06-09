package query.dql;

import builder.clause.WhereClauseFactory;
import org.junit.Before;
import org.junit.Test;
import query.QueryFactory;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RowTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;
    private SelectQuery baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());

        this.baseQuery = queryFactory
                .select()
                .all()
                .from()
                .table("all_types")
                .build();
    }

    @Test
    public void testColumnCount() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(13, firstRow.getColumnCount());
    }

    @Test
    public void testGetIsCaseInsensitive() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals("Taunus", firstRow.getString("model"));
        assertEquals("Taunus", firstRow.getString("MODEL"));
    }

    @Test
    public void testUsesAliasNamesInsteadOfColumnNames() throws SQLException {
        SelectQuery query = queryFactory
                .select()
                    .column("person.id").alias("personId")
                    .column("course.id").alias("courseId")
                .from()
                    .table("person")
                .leftJoin("course").on("person.id", "course.person_id")
                .where(WhereClauseFactory.valueOf("firstname").equals("Miika"))
                .build();

        List<Row> result = query.execute();

        Row firstRow = result.get(0);

        assertColumnCount(result, 2);
        assertEquals(1, firstRow.getInteger("personId"));
        assertEquals(8, firstRow.getInteger("courseId"));
    }

    @Test
    public void testGetString() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals("Taunus", firstRow.getString("model"));
    }

    @Test
    public void testGetInteger() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        assertEquals(9, firstRow.getInteger("ID"));
    }

    @Test(expected = ClassCastException.class)
    public void testCastWrongType() throws SQLException {
        List<Row> result = baseQuery.execute();

        Row firstRow = result.get(0);

        firstRow.getString("ID");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateKeyThrowsException() throws SQLException {
        SelectQuery query = queryFactory
                .select()
                    .all()
                .from()
                    .table("person")
                    .table("address")
                .build();

        query.execute();
    }

}
