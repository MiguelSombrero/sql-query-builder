package builder.statement.select.order;

import builder.condition.Condition;

public class Having extends Orderer {

    public Having(StringBuilder queryString) {
        super(queryString);
    }

    public Having column(String columnName) {
        append(", ");
        validateAndAppend(columnName);
        return this;
    }

    public Orderer having(Condition havingClause) {
        append(" HAVING ");
        append(havingClause.build());
        return new Orderer(this.queryString);
    }
}
