package factory;

import builder.insert.Table;
import builder.select.field.FirstField;

public class QueryFactory {

    public static FirstField select() {
        return selectClause("SELECT ");
    }

    public static FirstField selectDistinct() {
        return selectClause("SELECT DISTINCT ");
    }

    public static Table inserInto() {
        return new Table(new StringBuilder("INSERT INTO () VALUES ()"));
    }

    private static FirstField selectClause(String clause) {
        return new FirstField(new StringBuilder(clause));
    }
}
