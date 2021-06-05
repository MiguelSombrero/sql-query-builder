package builder.statement.select.column;

import builder.statement.select.table.From;
import query.dql.SelectQuery;

public class ToFrom {
    private SelectQuery query;

    public ToFrom(SelectQuery query) {
        this.query = query;
    }

    /**
     * Appends 'FROM' to a query string 'SELECT column(s) FROM'.
     *
     * @return From class which is used to append tables
     *  or sub-queries in 'FROM table' statement.
     */
    public From from() {
        query.append(" FROM ");
        return new From(query);
    }
}
