package builder.query.create.index;

import builder.query.create.TerminalCreateOperation;
import builder.appender.StringAppender;
import query.Statement;

public class IndexedColumn {
    private Statement statement;

    public IndexedColumn(Statement statement) {
        this.statement = statement;
    }

    /**
     * Validates user input and appends 'column(s)'
     * into 'CREATE INDEX index ON table (column(s))' statement.
     *
     * @param listOfColumns List of columns index is being created
     *
     * @return TerminalCreateOperation which can be used only
     * to terminate query building and return CreateQuery
     */
    public TerminalCreateOperation columns(String ...listOfColumns) {
        statement.append(" ");
        StringAppender.validateAndAppendList(statement, listOfColumns);
        return new TerminalCreateOperation(statement);
    }
}
