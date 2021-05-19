package factory;

import builder.Query;
import builder.statement.create.Create;
import builder.statement.drop.Drop;
import builder.statement.select.column.FirstColumn;

public class QueryFactory {

    public static FirstColumn select() {
        return new FirstColumn(createQuery("SELECT "));
    }

    public static FirstColumn selectTop(int rows) {
        return new FirstColumn(createQuery("SELECT TOP " + rows + " "));
    }

    public static FirstColumn selectDistinct() {
        return new FirstColumn(createQuery("SELECT DISTINCT "));
    }

    public static builder.statement.insert.Table insertInto() {
        return new builder.statement.insert.Table(createQuery("INSERT INTO "));
    }

    public static builder.statement.update.Table update() {
        return new builder.statement.update.Table(createQuery("UPDATE "));
    }

    public static Create create() {
        return new Create(createQuery("CREATE "));
    }

    public static builder.statement.delete.Table deleteFrom() {
        return new builder.statement.delete.Table(createQuery("DELETE FROM "));
    }

    public static Drop drop() {
        return new Drop(createQuery("DROP "));
    }

    private static Query createQuery(String clause) {
        return new Query(new StringBuilder(clause));
    }
}
