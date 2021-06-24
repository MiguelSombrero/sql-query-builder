package builder.query.select.column;

import builder.query.select.table.From;
import query.Clause;

public class AliasedColumn extends ColumnTemplate {

    public AliasedColumn(Clause clause) {
        super(clause);
    }

    /**
     * Appends 'FROM' to a query string 'SELECT column(s) FROM'.
     *
     * @return From class which is used to append tables
     *  or sub-queries in 'FROM table' statement.
     */
    public From from() {
        clause.append(" FROM ");
        return new From(clause);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        clause.append(", ");
    }
}
