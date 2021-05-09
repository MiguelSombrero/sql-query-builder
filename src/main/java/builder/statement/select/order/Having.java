package builder.statement.select.order;

import builder.clause.where.Condition;

public class Having extends Orderer {

    public Having(StringBuilder builder) {
        super(builder);
    }

    public Having column(String columnName) {
        append(", ");
        append(columnName);
        return this;
    }

    public Orderer having(Condition havingClause) {
        append(" HAVING ");
        append(havingClause.build());
        return new Orderer(this.builder);
    }
}
