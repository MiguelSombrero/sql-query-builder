package builder.statement;

import builder.query.select.SelectQueryBuilder;
import query.Statement;
import query.SQLStatement;

public class StatementFactory {

    public static Statement createStatement(String clause) {
        return new SQLStatement(createQueryString(clause));
    }

    public static Statement createEmptyStatement() {
        return createStatement("");
    }

    public static Statement createExistsStatement(SelectQueryBuilder subQuery) {
        return createStatement("EXISTS (" + subQuery.build() + ")");
    }

    public static Statement createNotExistsStatement(SelectQueryBuilder subQuery) {
        return createStatement("NOT EXISTS (" + subQuery.build() + ")");
    }

    private static StringBuilder createQueryString(String queryString) {
        return new StringBuilder(queryString);
    }
}
