package builder.clause;

import builder.query.select.SelectQueryBuilder;
import clause.SQLClause;
import validation.Validator;
import validation.ValidatorFactory;

public class WhereClauseFactory {
    private static Validator<String> validator = ValidatorFactory.exceptionThrowingNameValidator();

    public static Negation valueOf(String column) {
        validator.validate(column);
        return new Negation(createQuery(column));
    }

    public static Condition exists(SelectQueryBuilder subQuery) {
        SQLClause query = createQuery("EXISTS (" + subQuery.build() + ")");
        return new Condition(query);
    }

    public static Condition notExists(SelectQueryBuilder subQuery) {
        SQLClause query = createQuery("NOT EXISTS (" + subQuery.build() + ")");
        return new Condition(query);
    }

    private static SQLClause createQuery(String clause) {
        return new SQLClause(new StringBuilder(clause));
    }
}
