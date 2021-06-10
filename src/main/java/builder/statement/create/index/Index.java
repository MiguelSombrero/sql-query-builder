package builder.statement.create.index;

import builder.appender.StringAppender;
import query.ddl.CreateQuery;

public class Index {
    private CreateQuery query;

    public Index(CreateQuery query) {
        this.query = query;
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
        StringAppender.validateAndAppend(query, table);
        return new IndexedColumn(query);
    }
}
