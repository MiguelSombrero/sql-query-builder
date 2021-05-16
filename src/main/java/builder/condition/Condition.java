package builder.condition;

import builder.Builder;
import builder.TerminalOperation;

public class Condition extends TerminalOperation {

    public Condition(StringBuilder queryString) {
        super(queryString);
    }

    public Condition and(Builder condition) {
        append(" AND ");
        append(condition.build());
        return this;
    }

    public Condition or(Builder condition) {
        append(" OR ");
        append(condition.build());
        return this;
    }

    public Condition orSub(Builder condition) {
        append(" OR (");
        append(condition.build());
        append(")");
        return this;
    }
}