package builder.query.create;

import builder.query.create.index.Index;
import builder.query.create.table.column.FirstColumn;
import builder.appender.StringAppender;
import query.Statement;

public class Create {
    private Statement statement;

    public Create(Statement statement) {
        this.statement = statement;
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
        statement.append("TABLE ");
        StringAppender.validateAndAppend(statement, name);
        statement.append(" (");
        return new FirstColumn(statement);
    }

    /**
     * Validates user input and appends 'DATABASE name' into
     * 'CREATE DATABASE name' statement.
     *
     * @param name database name to be created
     *
     * @return TerminalCreateOperation which can be used only
     * to terminate query building
     */
    public TerminalCreateOperation database(String name) {
        statement.append("DATABASE ");
        StringAppender.validateAndAppend(statement, name);
        return new TerminalCreateOperation(statement);
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
        statement.append("INDEX ");
        StringAppender.validateAndAppend(statement, name);
        return new Index(statement);
    }
}
