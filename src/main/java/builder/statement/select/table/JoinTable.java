package builder.statement.select.table;

import builder.condition.Condition;
import builder.statement.select.order.Grouper;
import factory.ValidatorFactory;
import validation.Validator;

public class JoinTable extends Grouper {
    protected static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public JoinTable(StringBuilder queryString) {
        super(queryString);
    }

    public Grouper where(Condition clause) {
        append(" WHERE ");
        append(clause.build());
        return new Grouper(this.queryString);
    }

    public On innerJoin(String table) {
        append(" INNER JOIN ");
        return join(table);
    }

    public On leftJoin(String table) {
        append(" LEFT JOIN ");
        return join(table);
    }

    public On rightJoin(String table) {
        append(" RIGHT JOIN ");
        return join(table);
    }

    private On join(String table) {
        validator.validate(table);
        append(table);
        return new On(this.queryString);
    }
}
