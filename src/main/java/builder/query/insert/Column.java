package builder.query.insert;

import builder.query.select.SelectQueryBuilder;
import query.Statement;

public class Column {

    protected Statement statement;

    public Column(Statement statement) {
        this.statement = statement;
    }

    /**
     * Appends 'VALUES (' into 'INSERT INTO table VALUE (...' statement.
     *
     * @return FirstValue class which can be used to append
     * first value (value without comma) into 'VALUE (...)'
     * statement
     */
    public FirstValue values() {
        statement.append("VALUES (");
        return new FirstValue(statement);
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
        statement.mergeStatement(subQuery.build().getStatement());
        return new TerminalInsertOperation(statement);
    }
}
