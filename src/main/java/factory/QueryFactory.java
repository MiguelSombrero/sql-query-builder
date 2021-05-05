package factory;

import builder.clause.where.Negation;
import builder.statement.create.Create;
import builder.statement.drop.Drop;
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
        return new builder.statement.insert.Table(new StringBuilder("INSERT INTO "));
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

    public static Drop drop() {
        return new Drop(new StringBuilder("DROP "));
    }

    public static Negation valueOf(String operand) {
        return new Negation(new StringBuilder(" WHERE " + operand));
    }

    private static FirstColumn selectClause(String clause) {
        return new FirstColumn(new StringBuilder(clause));
    }
}
