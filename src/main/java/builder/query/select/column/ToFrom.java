package builder.query.select.column;

import builder.query.select.table.From;
import query.Clause;

public class ToFrom {
    private Clause clause;

    public ToFrom(Clause clause) {
        this.clause = clause;
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
}
