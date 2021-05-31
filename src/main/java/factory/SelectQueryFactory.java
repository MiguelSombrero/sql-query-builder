package factory;

import builder.statement.select.column.FirstColumn;
import query.SelectQuery;

import java.sql.Connection;

public class SelectQueryFactory {
    private Connection connection;

    public SelectQueryFactory(Connection connection) {
        this.connection = connection;
    }

    public FirstColumn select() {
        return new FirstColumn(createSelectQuery("SELECT "));
    }

    public FirstColumn selectTop(int rows) {
        return new FirstColumn(createSelectQuery("SELECT TOP " + rows + " "));
    }

    public FirstColumn selectDistinct() {
        return new FirstColumn(createSelectQuery("SELECT DISTINCT "));
    }

    private SelectQuery createSelectQuery(String clause) {
        return new SelectQuery(new StringBuilder(clause), connection);
    }
}
