package factory;

import builder.statement.select.column.FirstColumn;
import query.SelectQuery;

import javax.sql.DataSource;

public class SelectQueryFactory {
    private DataSource dataSource;

    public SelectQueryFactory(DataSource dataSource) {
        this.dataSource = dataSource;
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
        return new SelectQuery(new StringBuilder(clause), dataSource);
    }
}
