package factory;

import builder.Insert;
import builder.field.FirstField;

public class QueryFactory {

    public static FirstField select() {
        return selectClause("SELECT ");
    }

    public static FirstField selectDistinct() {
        return selectClause("SELECT DISTINCT ");
    }

    public static Insert inserInto(String table) {
        StringBuilder builder = new StringBuilder("INSERT INTO ");
        builder.append(table);
        return new Insert(builder);
    }

    private static FirstField selectClause(String clause) {
        return new FirstField(new StringBuilder(clause));
    }
}
