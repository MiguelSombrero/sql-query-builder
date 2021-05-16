package builder.statement.select.order;

import builder.condition.Condition;

import javax.xml.bind.ValidationException;

public class Having extends Orderer {

    public Having(StringBuilder queryString) {
        super(queryString);
    }

    public Having column(String columnName) throws ValidationException {
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
