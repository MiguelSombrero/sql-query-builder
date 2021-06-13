package builder.clause;

import clause.SQLClause;

public class ClauseFactory {

    public static SQLClause createClause() {
        return new SQLClause(new StringBuilder());
    }

    public static SQLClause createClause(String clause) {
        return new SQLClause(new StringBuilder(clause));
    }
}
