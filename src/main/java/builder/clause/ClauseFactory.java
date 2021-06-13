package builder.clause;

import builder.query.select.SelectQueryBuilder;
import clause.SQLClause;

public class ClauseFactory {

    public static SQLClause createEmptyClause() {
        return new SQLClause(createQueryString(""));
    }

    public static SQLClause createExistsClause(SelectQueryBuilder subQuery) {
        return new SQLClause(createQueryString("EXISTS (" + subQuery.build() + ")"));
    }

    public static SQLClause createNotExistsClause(SelectQueryBuilder subQuery) {
        return new SQLClause(createQueryString("NOT EXISTS (" + subQuery.build() + ")"));
    }

    private static StringBuilder createQueryString(String queryString) {
        return new StringBuilder(queryString);
    }
}
