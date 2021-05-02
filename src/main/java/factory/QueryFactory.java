package factory;

import builder.statement.create.Create;
import builder.statement.select.column.FirstColumn;

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

    public static builder.statement.insert.Table insertInto() {
        return new builder.statement.insert.Table(new StringBuilder("INSERT INTO () VALUES ()"));
    }

    public static builder.statement.update.Table update() {
        return new builder.statement.update.Table(new StringBuilder("UPDATE "));
    }

    public static Create create() {
        return new Create(new StringBuilder("CREATE "));
    }

    public static builder.statement.delete.Table deleteFrom() {
        return new builder.statement.delete.Table(new StringBuilder("DELETE FROM "));
    }

    private static FirstColumn selectClause(String clause) {
        return new FirstColumn(new StringBuilder(clause));
    }
}
