package builder.query.insert;

import builder.query.select.SelectQueryBuilder;
import query.Clause;

public class Column {

    protected Clause clause;

    public Column(Clause clause) {
        this.clause = clause;
    }

    /**
     * Appends 'VALUES (' into 'INSERT INTO table VALUE (...' statement.
     *
     * @return FirstValue class which can be used to append
     * first value (value without comma) into 'VALUE (...)'
     * statement
     */
    public FirstValue values() {
        clause.append("VALUES (");
        return new FirstValue(clause);
    }

    /**
     * Appends 'SELECT sub-query' into 'INSERT INTO table SELECT sub-query'
     * statement.
     *
     * @param subQuery SELECT sub-query to be appended
     *
     * @return TerminalInsertOperation class which can be used only
     * to terminate query building and return InsertQuery
     */
    public TerminalInsertOperation sub(SelectQueryBuilder subQuery) {
        clause.mergeClause(subQuery.build().getClause());
        return new TerminalInsertOperation(clause);
    }
}
