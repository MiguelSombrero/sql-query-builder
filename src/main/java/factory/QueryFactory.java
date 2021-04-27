package factory;

import builder.create.Create;
import builder.select.field.FirstField;

public class QueryFactory {

    public static FirstField select() {
        return selectClause("SELECT ");
    }

    public static FirstField selectTop(int rows) {
        return selectClause("SELECT TOP " + rows + " ");
    }

    public static FirstField selectDistinct() {
        return selectClause("SELECT DISTINCT ");
    }

    public static builder.insert.Table insertInto() {
        return new builder.insert.Table(new StringBuilder("INSERT INTO () VALUES ()"));
    }

    public static Create create() {
        return new Create(new StringBuilder("CREATE "));
    }

    private static FirstField selectClause(String clause) {
        return new FirstField(new StringBuilder(clause));
    }
}
