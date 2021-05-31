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
        return new FirstColumn(createQuery("SELECT "));
    }

    public FirstColumn selectTop(int rows) {
        return new FirstColumn(createQuery("SELECT TOP " + rows + " "));
    }

    public FirstColumn selectDistinct() {
        return new FirstColumn(createQuery("SELECT DISTINCT "));
    }

    private SelectQuery createQuery(String clause) {
        return new SelectQuery(new StringBuilder(clause), connection);
    }
}
