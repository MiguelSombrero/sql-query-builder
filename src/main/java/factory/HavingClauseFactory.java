package factory;

import builder.Query;
import builder.condition.Negation;
import validation.Validator;

public class HavingClauseFactory {
    private static Validator validator = ValidatorFactory.exceptionThrowingColumnValidator();

    public static Negation count(String column) {
        return appendOperationAndReturn("COUNT", column);
    }

    public static Negation sum(String column) {
        return appendOperationAndReturn("SUM", column);
    }

    public static Negation avg(String column) {
        return appendOperationAndReturn("AVG", column);
    }

    public static Negation max(String column) {
        return appendOperationAndReturn("MAX", column);
    }

    public static Negation min(String column) {
        return appendOperationAndReturn("MIN", column);
    }

    private static Negation appendOperationAndReturn(String operation, String column) {
        validator.validate(column);
        return new Negation(createQuery(operation + "(" + column + ")"));
    }

    private static Query createQuery(String clause) {
        return new Query(new StringBuilder(clause));
    }
}
