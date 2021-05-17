package builder.statement.create.index;

import builder.SQLStringAppender;

public class Index extends SQLStringAppender {

    public Index(StringBuilder queryString) {
        super(queryString);
    }

    public IndexedColumn on(String table) {
        append(" ON ");
        validateAndAppend(table);
        return new IndexedColumn(this.queryString);
    }
}
