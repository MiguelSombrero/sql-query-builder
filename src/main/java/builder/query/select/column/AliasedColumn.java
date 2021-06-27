package builder.query.select.column;

import builder.query.select.table.From;
import query.Statement;

public class AliasedColumn extends ColumnTemplate {

    public AliasedColumn(Statement statement) {
        super(statement);
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

    @Override
    protected void addCommaAfterFirstValue() {
        statement.append(", ");
    }
}
