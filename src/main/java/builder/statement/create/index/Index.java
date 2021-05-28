package builder.statement.create.index;

import builder.Query;
import builder.utils.StringAppender;

public class Index {
    private StringAppender stringAppender;

    private Query query;

    public Index(Query query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
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
        query.append(" ON ");
        stringAppender.validateAndAppend(table);
        return new IndexedColumn(query);
    }
}
