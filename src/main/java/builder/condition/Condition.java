package builder.condition;

import builder.QueryBuilder;
import builder.Query;
import builder.TerminalOperation;

public class Condition extends TerminalOperation {

    public Condition(Query query) {
        super(query);
    }

    public Condition and(QueryBuilder condition) {
        query.append(" AND ");
        query.append(condition.build());
        return this;
    }

    public Condition or(QueryBuilder condition) {
        query.append(" OR ");
        query.append(condition.build());
        return this;
    }

    public Condition orSub(QueryBuilder condition) {
        query.append(" OR (");
        query.append(condition.build());
        query.append(")");
        return this;
    }
}
