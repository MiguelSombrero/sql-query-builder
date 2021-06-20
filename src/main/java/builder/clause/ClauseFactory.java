package builder.clause;

import builder.query.select.SelectQueryBuilder;
import query.Clause;
import query.SQLClause;

public class ClauseFactory {

    public static Clause createEmptyClause() {
        return new SQLClause(createQueryString(""));
    }

    public static Clause createExistsClause(SelectQueryBuilder subQuery) {
        return new SQLClause(createQueryString("EXISTS (" + subQuery.build() + ")"));
    }

    public static Clause createNotExistsClause(SelectQueryBuilder subQuery) {
        return new SQLClause(createQueryString("NOT EXISTS (" + subQuery.build() + ")"));
    }

    private static StringBuilder createQueryString(String queryString) {
        return new StringBuilder(queryString);
    }
}
