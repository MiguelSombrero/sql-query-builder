package builder.statement.select.column;

import builder.utils.StringAppender;
import query.SelectQuery;

/**
 * represents column after first column to be appended in
 * 'SELECT column(s), aggregate function(s)' statement.
 *
 * Adds comma before column name because it is not the first
 * column in a list of columns
 */
public class Column extends AliasedColumn {
    private StringAppender stringAppender;

    public Column(SelectQuery query) {
        super(query);
        this.stringAppender = new StringAppender(query);
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
        query.append(" AS ");
        stringAppender.validateAndAppend(as);
        return new AliasedColumn(query);
    }
}
