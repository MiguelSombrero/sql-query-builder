package builder.statement.select.table;

import builder.condition.Condition;
import builder.statement.select.order.Grouper;

import javax.xml.bind.ValidationException;

public class JoinTable extends Grouper {

    public JoinTable(StringBuilder queryString) {
        super(queryString);
    }

    public Grouper where(Condition clause) {
        append(" WHERE ");
        append(clause.build());
        return new Grouper(this.queryString);
    }

    public On innerJoin(String table) throws ValidationException {
        append(" INNER JOIN ");
        return join(table);
    }

    public On leftJoin(String table) throws ValidationException {
        append(" LEFT JOIN ");
        return join(table);
    }

    public On rightJoin(String table) throws ValidationException {
        append(" RIGHT JOIN ");
        return join(table);
    }

    private On join(String table) throws ValidationException {
        validateAndAppend(table);
        return new On(this.queryString);
    }
}
