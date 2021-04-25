package factory;

import builder.select.field.FirstField;

public class QueryFactory {

    public static FirstField select() {
        return selectClause("SELECT ");
    }

    public static FirstField selectDistinct() {
        return selectClause("SELECT DISTINCT ");
    }

    public static builder.insert.Table insertInto() {
        return new builder.insert.Table(new StringBuilder("INSERT INTO () VALUES ()"));
    }

    public static builder.create.Table create() {
        return new builder.create.Table(new StringBuilder("CREATE TABLE ()"));
    }

    private static FirstField selectClause(String clause) {
        return new FirstField(new StringBuilder(clause));
    }
}
