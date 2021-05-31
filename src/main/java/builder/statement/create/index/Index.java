package builder.statement.create.index;

import query.SQLQuery;
import builder.utils.StringAppender;

public class Index {
    private StringAppender stringAppender;

    private SQLQuery SQLQuery;

    public Index(SQLQuery SQLQuery) {
        this.SQLQuery = SQLQuery;
        this.stringAppender = new StringAppender(SQLQuery);
    }

    /**
     * Validates user input and appends 'ON table'
     * into 'CREATE INDEX index ON table (column(s))' statement.
     *
     * @param table Table name to index being appended
     *
     * @return IndexedColumn class which is used to
     * append column(s) into 'CREATE INDEX index ON
     * table (column(s))' statement
     */
    public IndexedColumn on(String table) {
        SQLQuery.append(" ON ");
        stringAppender.validateAndAppend(table);
        return new IndexedColumn(SQLQuery);
    }
}
