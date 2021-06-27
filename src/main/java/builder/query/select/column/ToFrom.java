package builder.query.select.column;

import builder.query.select.table.From;
import query.Statement;

public class ToFrom {
    private Statement statement;

    public ToFrom(Statement statement) {
        this.statement = statement;
    }

    /**
     * Appends 'FROM' to a query string 'SELECT column(s) FROM'.
     *
     * @return From class which is used to append tables
     *  or sub-queries in 'FROM table' statement.
     */
    public From from() {
        statement.append(" FROM ");
        return new From(statement);
    }
}
