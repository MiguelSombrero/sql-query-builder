package builder.query.insert;

import builder.query.select.SelectQueryBuilder;
import query.InsertQuery;

public class Column {

    protected InsertQuery query;

    public Column(InsertQuery query) {
        this.query = query;
    }

    /**
     * Appends 'VALUES (' into 'INSERT INTO table VALUE (...' statement.
     *
     * @return FirstValue class which can be used to append
     * first value (value without comma) into 'VALUE (...)'
     * statement
     */
    public FirstValue values() {
        query.append("VALUES (");
        return new FirstValue(query);
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
        query.mergeClause(subQuery.build());
        return new TerminalInsertOperation(query);
    }
}
