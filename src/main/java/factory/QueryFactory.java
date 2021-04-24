package factory;

import builder.field.FirstField;
import builder.insert.FirstColumn;

public class QueryFactory {

    public static FirstField select() {
        return selectClause("SELECT ");
    }

    public static FirstField selectDistinct() {
        return selectClause("SELECT DISTINCT ");
    }

    public static FirstColumn inserInto(String table) {
        StringBuilder builder = new StringBuilder("INSERT INTO ");
        builder.append(table);
        builder.append(" () VALUES ()");
        return new FirstColumn(builder);
    }

    private static FirstField selectClause(String clause) {
        return new FirstField(new StringBuilder(clause));
    }
}
