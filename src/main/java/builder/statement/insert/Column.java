package builder.statement.insert;

import builder.Query;
import builder.TerminalOperation;
import builder.statement.select.SelectQueryBuilder;

public class Column {

    protected Query query;

    public Column(Query query) {
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
     * @return TerminalOperation class which can be used only
     * to terminate query building
     */
    public TerminalOperation sub(SelectQueryBuilder subQuery) {
        query.append(subQuery.build());
        return new TerminalOperation(query);
    }
}
