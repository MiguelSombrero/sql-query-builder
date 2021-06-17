package builder.query;

import builder.query.create.Create;
import builder.query.delete.DeleteTable;
import builder.query.drop.Drop;
import builder.query.insert.InsertTable;
import builder.query.select.column.FirstColumn;
import builder.query.update.UpdateTable;
import query.ddl.CreateQuery;
import query.ddl.DropQuery;
import query.dml.DeleteQuery;
import query.dml.InsertQuery;
import query.dml.UpdateQuery;
import query.dql.SelectQuery;

import javax.sql.DataSource;

public class SQLQueryBuilder {
    private SQLQueryFactory sqlQueryFactory;

    public SQLQueryBuilder(DataSource dataSource) {
        this.sqlQueryFactory = new SQLQueryFactory(dataSource);
    }

    public FirstColumn select() {
        SelectQuery query = sqlQueryFactory.createSelectQuery();
        return new FirstColumn(query);
    }

    public FirstColumn selectDistinct() {
        SelectQuery query = sqlQueryFactory.createSelectDistinctQuery();
        return new FirstColumn(query);
    }

    public FirstColumn selectTop(int rows) {
        SelectQuery query = sqlQueryFactory.createSelectTopQuery(rows);
        return new FirstColumn(query);
    }

    public InsertTable insert() {
        InsertQuery query = sqlQueryFactory.createInsertQuery();
        return new InsertTable(query);
    }

    public DeleteTable delete() {
        DeleteQuery query = sqlQueryFactory.createDeleteQuery();
        return new DeleteTable(query);
    }

    public UpdateTable update() {
        UpdateQuery query = sqlQueryFactory.createUpdateQuery();
        return new UpdateTable(query);
    }

    public Create create() {
        CreateQuery query = sqlQueryFactory.createCreateQuery();
        return new Create(query);
    }

    public Drop drop() {
        DropQuery query = sqlQueryFactory.createDropQuery();
        return new Drop(query);
    }
}
