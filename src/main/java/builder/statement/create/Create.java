package builder.statement.create;

import query.DDLQuery;
import builder.TerminalOperation;
import builder.statement.create.index.Index;
import builder.statement.create.table.column.FirstColumn;
import builder.utils.StringAppender;

public class Create {
    private StringAppender stringAppender;

    private DDLQuery query;

    public Create(DDLQuery query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
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
        query.append("TABLE ");
        stringAppender.validateAndAppend(name);
        query.append(" (");
        return new FirstColumn(query);
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
        query.append("DATABASE ");
        stringAppender.validateAndAppend(name);
        return new TerminalOperation(query);
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
        query.append("INDEX ");
        stringAppender.validateAndAppend(name);
        return new Index(query);
    }
}
