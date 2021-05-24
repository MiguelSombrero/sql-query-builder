package builder.statement.select.table;

import builder.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class AliasedOn {
    protected static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    protected Query query;

    public AliasedOn(Query query) {
        this.query = query;
    }

    /**
     * Appends 'ON column = joinColumn' into query string
     * 'SELECT ... JOIN table ON column = joinColumn'.
     *
     * @param column First column name used for join
     *
     * @param joinColumn Second column name user for join
     *
     * @return JoinTable class which can be used to create
     * more joins or proceed to WHERE, GROUP BY, etc. clauses
     */
    public JoinTable on(String column, String joinColumn) {
        validator.validate(column);
        validator.validate(joinColumn);
        query.append(" ON ");
        query.append(column);
        query.append(" = ");
        query.append(joinColumn);
        return new JoinTable(query);
    }
}
