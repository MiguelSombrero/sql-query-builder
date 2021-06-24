package builder.clause;

import builder.query.select.SelectQueryBuilder;
import query.Clause;
import query.SQLClause;

public class ClauseFactory {

    public static Clause createClause(String clause) {
        return new SQLClause(createQueryString(clause));
    }

    public static Clause createEmptyClause() {
        return createClause("");
    }

    public static Clause createExistsClause(SelectQueryBuilder subQuery) {
        return createClause("EXISTS (" + subQuery.build() + ")");
    }

    public static Clause createNotExistsClause(SelectQueryBuilder subQuery) {
        return createClause("NOT EXISTS (" + subQuery.build() + ")");
    }

    private static StringBuilder createQueryString(String queryString) {
        return new StringBuilder(queryString);
    }
}
