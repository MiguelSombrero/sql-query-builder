package builder.query.create.index;

import builder.appender.StringAppender;
import query.Statement;

public class Index {
    private Statement statement;

    public Index(Statement statement) {
        this.statement = statement;
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
        statement.append(" ON ");
        StringAppender.validateAndAppend(statement, table);
        return new IndexedColumn(statement);
    }
}
