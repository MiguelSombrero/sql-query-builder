package builder.query.insert;

import builder.appender.StringAppender;
import query.dml.InsertQuery;

public class InsertTable {
    private InsertQuery query;

    public InsertTable(InsertQuery query) {
        this.query = query;
    }

    /**
     * Validates user input and appends
     * 'table' into 'INSERT INTO table' statement.
     *
     * @param table Table name to be appended
     *
     * @return Insert class which can be used to
     * insert column(s) into INSERT INTO statement.
     */
    public Insert table(String table) {
        StringAppender.validateAndAppend(query, table);
        query.append(" ");
        return new Insert(query);
    }
}
