package factory;

import builder.Insert;
import builder.Select;

public class QueryFactory {

    public static Select select() {
        return new Select(new StringBuilder("SELECT "));
    }

    public static Insert inserInto(String table) {
        StringBuilder builder = new StringBuilder("INSERT INTO ");
        builder.append(table);
        return new Insert(builder);
    }
}
