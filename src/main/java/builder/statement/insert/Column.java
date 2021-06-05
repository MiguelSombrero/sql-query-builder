package builder.statement.insert;

import builder.TerminalDMLOperation;
import query.dml.DMLQuery;
import builder.statement.select.SelectQueryBuilder;

public class Column {

    protected DMLQuery query;

    public Column(DMLQuery query) {
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
     * @return TerminalDMLOperation class which can be used only
     * to terminate DML type query building
     */
    public TerminalDMLOperation sub(SelectQueryBuilder subQuery) {
        query.append(subQuery.build().toString());
        return new TerminalDMLOperation(query);
    }
}
