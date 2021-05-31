package builder.statement.create;

import query.SQLQuery;
import builder.TerminalOperation;
import builder.statement.create.index.Index;
import builder.statement.create.table.column.FirstColumn;
import builder.utils.StringAppender;

public class Create {
    private StringAppender stringAppender;

    private SQLQuery SQLQuery;

    public Create(SQLQuery SQLQuery) {
        this.SQLQuery = SQLQuery;
        this.stringAppender = new StringAppender(SQLQuery);
    }

    /**
     * Validates user input and appends 'TABLE name (' into
     * 'CREATE TABLE name (column datatype, ...)' statement.
     *
     * @param name Table name to be created
     *
     * @return FirstColumn class which can be used to
     * append column into 'CREATE TABLE name
     * (column datatype, ...)' statement.
     */
    public FirstColumn table(String name) {
        SQLQuery.append("TABLE ");
        stringAppender.validateAndAppend(name);
        SQLQuery.append(" (");
        return new FirstColumn(SQLQuery);
    }

    /**
     * Validates user input and appends 'DATABASE name' into
     * 'CREATE DATABASE name' statement.
     *
     * @param name database name to be created
     *
     * @return TerminalOperation which can be used only
     * to terminate query building
     */
    public TerminalOperation database(String name) {
        SQLQuery.append("DATABASE ");
        stringAppender.validateAndAppend(name);
        return new TerminalOperation(SQLQuery);
    }

    /**
     * Validates user input and appends 'INDEX name' into
     * 'CREATE INDEX name ON table (column(s))' statement.
     *
     * @param name Index name to be created
     *
     * @return Index class which can be used to
     * select table and columns index is assigned to
     */
    public Index index(String name) {
        SQLQuery.append("INDEX ");
        stringAppender.validateAndAppend(name);
        return new Index(SQLQuery);
    }
}
