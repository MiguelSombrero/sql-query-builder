package factory;

import builder.statement.create.Create;
import builder.statement.drop.Drop;
import builder.statement.select.column.FirstColumn;
import query.DDLQuery;
import query.DMLQuery;
import query.SelectQuery;

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
        DMLQuery query = createDMLQuery("INSERT INTO ");
        return new builder.statement.insert.Table(query);
    }

    public builder.statement.delete.Table deleteFrom() {
        DMLQuery query = createDMLQuery("DELETE FROM ");
        return new builder.statement.delete.Table(query);
    }

    public builder.statement.update.Table update() {
        DMLQuery query = createDMLQuery("UPDATE ");
        return new builder.statement.update.Table(query);
    }

    public Create create() {
        DDLQuery query = createDDLQuery("CREATE ");
        return new Create(query);
    }

    public Drop drop() {
        DDLQuery query = createDDLQuery("DROP ");
        return new Drop(query);
    }

    private SelectQuery createSelectQuery(String clause) {
        return new SelectQuery(new StringBuilder(clause), dataSource);
    }

    private DMLQuery createDMLQuery(String clause) {
        return new DMLQuery(new StringBuilder(clause), dataSource);
    }

    private DDLQuery createDDLQuery(String clause) {
        return new DDLQuery(new StringBuilder(clause), dataSource);
    }
}
