package builder.statement.insert;

import query.SQLQuery;
import builder.TerminalOperation;
import builder.statement.select.SelectQueryBuilder;

public class Column {

    protected SQLQuery SQLQuery;

    public Column(SQLQuery SQLQuery) {
        this.SQLQuery = SQLQuery;
    }

    /**
     * Appends 'VALUES (' into 'INSERT INTO table VALUE (...' statement.
     *
     * @return FirstValue class which can be used to append
     * first value (value without comma) into 'VALUE (...)'
     * statement
     */
    public FirstValue values() {
        SQLQuery.append("VALUES (");
        return new FirstValue(SQLQuery);
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
        SQLQuery.append(subQuery.build().toString());
        return new TerminalOperation(SQLQuery);
    }
}
