package builder.statement.select.table;

import builder.Query;
import builder.condition.Condition;
import builder.statement.select.order.Grouper;
import factory.ValidatorFactory;
import validation.Validator;

public class JoinTable extends Grouper {
    protected static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public JoinTable(Query query) {
        super(query);
    }

    public Grouper where(Condition clause) {
        query.append(" WHERE ");
        query.append(clause.build());
        return new Grouper(query);
    }

    public On innerJoin(String table) {
        query.append(" INNER JOIN ");
        return join(table);
    }

    public On leftJoin(String table) {
        query.append(" LEFT JOIN ");
        return join(table);
    }

    public On rightJoin(String table) {
        query.append(" RIGHT JOIN ");
        return join(table);
    }

    private On join(String table) {
        validator.validate(table);
        query.append(table);
        return new On(query);
    }
}
