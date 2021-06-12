package builder.clause;

import builder.statement.select.SelectQueryBuilder;
import query.SQLQuery;
import validation.Validator;
import validation.ValidatorFactory;

public class WhereClauseFactory {
    private static Validator<String> validator = ValidatorFactory.exceptionThrowingNameValidator();

    public static Negation valueOf(String operand) {
        validator.validate(operand);
        return new Negation(createQuery(operand));
    }

    public static Condition exists(SelectQueryBuilder subQuery) {
        SQLQuery query = createQuery("EXISTS (" + subQuery.build() + ")");
        return new Condition(query);
    }

    public static Condition notExists(SelectQueryBuilder subQuery) {
        SQLQuery query = createQuery("NOT EXISTS (" + subQuery.build() + ")");
        return new Condition(query);
    }

    private static SQLQuery createQuery(String clause) {
        return new SQLQuery(new StringBuilder(clause));
    }
}
