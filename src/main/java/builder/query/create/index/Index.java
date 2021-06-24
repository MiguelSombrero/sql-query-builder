package builder.query.create.index;

import builder.appender.StringAppender;
import query.Clause;

public class Index {
    private Clause clause;

    public Index(Clause clause) {
        this.clause = clause;
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
        clause.append(" ON ");
        StringAppender.validateAndAppend(clause, table);
        return new IndexedColumn(clause);
    }
}
