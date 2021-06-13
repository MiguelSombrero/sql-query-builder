package builder.query.delete;

import builder.appender.StringAppender;
import query.dml.DeleteQuery;

public class Table {
    private DeleteQuery query;

    public Table(DeleteQuery query) {
        this.query = query;
    }

    /**
     * Validates user input and appends 'table'
     * into 'DELETE TABLE table' statement.
     *
     * @param table Table name to be appended
     *
     * @return Condition class which can be used to append
     * WHERE clause to the DELETE statement, or terminate
     * query building
     */
    public Where table(String table) {
        StringAppender.validateAndAppend(query, table);
        return new Where(query);
    }
}
