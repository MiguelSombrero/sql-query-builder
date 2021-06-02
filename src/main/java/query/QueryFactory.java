package query;

import builder.statement.create.Create;
import builder.statement.drop.Drop;
import builder.statement.select.column.FirstColumn;
import query.ddl.CreateQuery;
import query.dml.DeleteQuery;
import query.dml.InsertQuery;
import query.dml.UpdateQuery;
import query.dql.SelectQuery;

import javax.sql.DataSource;

public class QueryFactory {
    private DataSource dataSource;

    public QueryFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public FirstColumn select() {
        SelectQuery query = createSelectQuery("SELECT ");
        return new FirstColumn(query);
    }

    public FirstColumn selectTop(int rows) {
        SelectQuery query = createSelectQuery("SELECT TOP " + rows + " ");
        return new FirstColumn(query);
    }

    public FirstColumn selectDistinct() {
        SelectQuery query = createSelectQuery("SELECT DISTINCT ");
        return new FirstColumn(query);
    }

    public builder.statement.insert.Table insertInto() {
        InsertQuery query = createInsertQuery("INSERT INTO ");
        return new builder.statement.insert.Table(query);
    }

    public builder.statement.delete.Table deleteFrom() {
        DeleteQuery query = createDeleteQuery("DELETE FROM ");
        return new builder.statement.delete.Table(query);
    }

    public builder.statement.update.Table update() {
        UpdateQuery query = createUpdateQuery("UPDATE ");
        return new builder.statement.update.Table(query);
    }

    public Create create() {
        CreateQuery query = createCreateQuery("CREATE ");
        return new Create(query);
    }

    public Drop drop() {
        CreateQuery query = createDDLQuery("DROP ");
        return new Drop(query);
    }

    private SelectQuery createSelectQuery(String clause) {
        return new SelectQuery(new StringBuilder(clause), dataSource);
    }

    private InsertQuery createInsertQuery(String clause) {
        return new InsertQuery(new StringBuilder(clause), dataSource);
    }

    private UpdateQuery createUpdateQuery(String clause) {
        return new UpdateQuery(new StringBuilder(clause), dataSource);
    }

    private DeleteQuery createDeleteQuery(String clause) {
        return new DeleteQuery(new StringBuilder(clause), dataSource);
    }

    private CreateQuery createCreateQuery(String clause) {
        return new CreateQuery(new StringBuilder(clause), dataSource);
    }

    private CreateQuery createDDLQuery(String clause) {
        return new CreateQuery(new StringBuilder(clause), dataSource);
    }
}