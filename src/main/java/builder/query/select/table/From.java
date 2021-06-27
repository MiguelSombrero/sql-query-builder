package builder.query.select.table;

import builder.query.select.SelectQueryBuilder;
import builder.appender.StringAppender;
import query.Statement;

public class From {
    private Statement statement;

    public From(Statement statement) {
        this.statement = statement;
    }

    /**
     * Validates user input and appends
     * 'table' into 'FROM table' statement.
     *
     * @param table Table name to be queried from
     *
     * @return Table class which can be used to append
     * tables in FROM statement, alias tables and proceed
     * to WHERE, JOIN, GROUP BY etc. statements
     */
    public Table table(String table) {
        StringAppender.validateAndAppend(statement, table);
        return new Table(statement);
    }

    /**
     * Appends sub-select in 'SELECT ... FROM (SELECT ...)' statement.
     * Sub-select is created with factory class QueryFactory
     *
     * @param subQuery Sub-select to be inserted in FROM statement
     *
     * @return SubQuery class which can be used to
     * alias sub-query or proceed to WHERE and JOIN statements
     */
    public SubQuery sub(SelectQueryBuilder subQuery) {
        statement.append("(");
        statement.mergeStatement(subQuery.build().getStatement());
        statement.append(")");
        return new SubQuery(statement);
    }
}
