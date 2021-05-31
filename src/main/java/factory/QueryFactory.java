package factory;

import query.SQLQuery;
import builder.statement.create.Create;
import builder.statement.drop.Drop;

public class QueryFactory {

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

    private static SQLQuery createQuery(String clause) {
        return new SQLQuery(new StringBuilder(clause));
    }
}
