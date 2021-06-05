package builder.condition;

import builder.statement.select.SelectQueryBuilder;
import query.Query;
import query.SQLQuery;
import validation.Validator;
import validation.ValidatorFactory;

public class WhereClauseFactory {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public static Negation valueOf(String operand) {
        validator.validate(operand);
        return new Negation(createQuery(operand));
    }

    public static Condition exists(SelectQueryBuilder subQuery) {
        Query query = createQuery("EXISTS (" + subQuery.build() + ")");
        return new Condition(query);
    }

    public static Condition notExists(SelectQueryBuilder subQuery) {
        Query query = createQuery("NOT EXISTS (" + subQuery.build() + ")");
        return new Condition(query);
    }

    private static Query createQuery(String clause) {
        return new SQLQuery(new StringBuilder(clause));
    }
}
