package builder.query.select.column;

import builder.appender.StringAppender;
import query.Clause;

/**
 * represents column after first column to be appended in
 * 'SELECT column(s), aggregate function(s)' statement.
 *
 * Adds comma before column name because it is not the first
 * column in a list of columns
 */
public class Column extends AliasedColumn {

    public Column(Clause clause) {
        super(clause);
    }

    /**
     * Validates user input and appends 'AS alias' into
     * 'SELECT column AS alias' statement
     *
     * @param as alias name to be appended after 'AS'
     *
     * @return AliasedColumn which has the same properties as Columns
     * except it cannot be aliased
     */
    public AliasedColumn alias(String as) {
        clause.append(" AS ");
        StringAppender.validateAndAppend(clause, as);
        return new AliasedColumn(clause);
    }
}
