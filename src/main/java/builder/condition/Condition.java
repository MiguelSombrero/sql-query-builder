package builder.condition;

import builder.Builder;
import builder.Query;
import builder.TerminalOperation;

public class Condition extends TerminalOperation {

    public Condition(Query query) {
        super(query);
    }

    public Condition and(Builder condition) {
        query.append(" AND ");
        query.append(condition.build());
        return this;
    }

    public Condition or(Builder condition) {
        query.append(" OR ");
        query.append(condition.build());
        return this;
    }

    public Condition orSub(Builder condition) {
        query.append(" OR (");
        query.append(condition.build());
        query.append(")");
        return this;
    }
}
