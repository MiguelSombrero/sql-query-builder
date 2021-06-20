package builder.query.select.table;

import builder.appender.StringAppender;
import query.SelectQuery;

public class On extends AliasedOn {

    public On(SelectQuery query) {
        super(query);
    }

    /**
     * Validates user input and appends 'AS alias' into
     * 'SELECT ... JOIN table AS alias' statement.
     *
     * @param alias Alias name to join table
     *
     * @return On class that can be used to
     * append 'ON column = joinColumn' into
     * join statement and alias join table
     */
    public On alias(String alias) {
        query.append(" AS ");
        StringAppender.validateAndAppend(query, alias);
        return this;
    }
}
