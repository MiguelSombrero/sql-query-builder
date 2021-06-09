package builder.statement.select.table;

import builder.appender.StringAppender;
import query.dql.SelectQuery;

public class AliasedOn {
    protected StringAppender stringAppender;

    protected SelectQuery query;

    public AliasedOn(SelectQuery query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
    }

    /**
     * Validates user input and appends 'ON column = joinColumn' into
     * query string 'SELECT ... JOIN table ON column = joinColumn'.
     *
     * @param column First column name used for join
     *
     * @param joinColumn Second column name user for join
     *
     * @return JoinTable class which can be used to create
     * more joins or proceed to WHERE, GROUP BY, etc. clauses
     */
    public JoinTable on(String column, String joinColumn) {
        query.append(" ON ");
        stringAppender.validateAndAppend(column);
        query.append(" = ");
        stringAppender.validateAndAppend(joinColumn);
        return new JoinTable(query);
    }
}
