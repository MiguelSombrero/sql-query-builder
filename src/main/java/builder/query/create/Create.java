package builder.query.create;

import builder.query.create.index.Index;
import builder.query.create.table.column.FirstColumn;
import builder.appender.StringAppender;
import query.Clause;

public class Create {
    private Clause clause;

    public Create(Clause clause) {
        this.clause = clause;
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
        clause.append("TABLE ");
        StringAppender.validateAndAppend(clause, name);
        clause.append(" (");
        return new FirstColumn(clause);
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
        clause.append("DATABASE ");
        StringAppender.validateAndAppend(clause, name);
        return new TerminalCreateOperation(clause);
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
        clause.append("INDEX ");
        StringAppender.validateAndAppend(clause, name);
        return new Index(clause);
    }
}
