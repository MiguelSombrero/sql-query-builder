package factory;

import builder.Builder;
import builder.clause.where.Condition;
import builder.clause.where.Negation;

public class WhereClauseFactory {

    public static Negation valueOf(String operand) {
        return new Negation(new StringBuilder(" WHERE " + operand));
    }

    public static Condition exists(Builder query) {
        return new Condition(new StringBuilder(" WHERE EXISTS (" + query.build() + ")"));
    }

    public static Condition notExists(Builder query) {
        return new Condition(new StringBuilder(" WHERE NOT EXISTS (" + query.build() + ")"));
    }
}
