package factory;

import builder.create.Create;
import builder.select.column.FirstColumn;

public class QueryFactory {

    public static FirstColumn select() {
        return selectClause("SELECT ");
    }

    public static FirstColumn selectTop(int rows) {
        return selectClause("SELECT TOP " + rows + " ");
    }

    public static FirstColumn selectDistinct() {
        return selectClause("SELECT DISTINCT ");
    }

    public static builder.insert.Table insertInto() {
        return new builder.insert.Table(new StringBuilder("INSERT INTO () VALUES ()"));
    }

    public static Create create() {
        return new Create(new StringBuilder("CREATE "));
    }

    private static FirstColumn selectClause(String clause) {
        return new FirstColumn(new StringBuilder(clause));
    }
}
