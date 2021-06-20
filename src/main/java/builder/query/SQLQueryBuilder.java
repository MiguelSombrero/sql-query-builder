package builder.query;

import builder.query.create.Create;
import builder.query.delete.DeleteTable;
import builder.query.drop.Drop;
import builder.query.insert.InsertTable;
import builder.query.select.column.FirstColumn;
import builder.query.update.UpdateTable;
import query.CreateQuery;
import query.DropQuery;
import query.DeleteQuery;
import query.InsertQuery;
import query.UpdateQuery;
import query.SelectQuery;

import javax.sql.DataSource;

public class SQLQueryBuilder {
    private SQLQueryFactory sqlQueryFactory;

    public SQLQueryBuilder(DataSource dataSource) {
        this.sqlQueryFactory = new SQLQueryFactory(dataSource);
    }

    /**
     * Creates new SELECT statement.
     *
     * @return FirstColumn class which starts SELECT
     * statement builder.
     */
    public FirstColumn select() {
        SelectQuery query = sqlQueryFactory.createSelectQuery();
        return new FirstColumn(query);
    }

    /**
     * Creates new SELECT DISTINCT statement.
     *
     * @return FirstColumn class which starts SELECT
     * statement builder.
     */
    public FirstColumn selectDistinct() {
        SelectQuery query = sqlQueryFactory.createSelectDistinctQuery();
        return new FirstColumn(query);
    }

    /**
     * Creates new SELECT TOP 'rows' statement.
     *
     * @return FirstColumn class which starts SELECT
     * statement builder.
     */
    public FirstColumn selectTop(int rows) {
        SelectQuery query = sqlQueryFactory.createSelectTopQuery(rows);
        return new FirstColumn(query);
    }

    /**
     * Creates new INSERT statement.
     *
     * @return InsertTable class which starts INSERT
     * statement builder.
     */
    public InsertTable insert() {
        InsertQuery query = sqlQueryFactory.createInsertQuery();
        return new InsertTable(query);
    }

    /**
     * Creates new DELETE statement.
     *
     * @return DeleteTable class which starts DELETE
     * statement builder.
     */
    public DeleteTable delete() {
        DeleteQuery query = sqlQueryFactory.createDeleteQuery();
        return new DeleteTable(query);
    }

    /**
     * Creates new UPDATE statement.
     *
     * @return UpdateTable class which starts UPDATE
     * statement builder.
     */
    public UpdateTable update() {
        UpdateQuery query = sqlQueryFactory.createUpdateQuery();
        return new UpdateTable(query);
    }

    /**
     * Creates new CREATE statement.
     *
     * @return Create class which starts CREATE
     * statement builder.
     */
    public Create create() {
        CreateQuery query = sqlQueryFactory.createCreateQuery();
        return new Create(query);
    }

    /**
     * Creates new DROP statement.
     *
     * @return Drop class which starts DROP
     * statement builder.
     */
    public Drop drop() {
        DropQuery query = sqlQueryFactory.createDropQuery();
        return new Drop(query);
    }
}
