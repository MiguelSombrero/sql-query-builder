package builder.statement.select.column;

/**
 * represents column after first column to be appended in
 * 'SELECT column(s), aggregate function(s)' statement.
 *
 * Adds comma before column name because it is not the first
 * column in a list of columns
 */
public class Column extends AliasedColumn {

    public Column(StringBuilder queryString) {
        super(queryString);
    }

    /**
     * Appends 'AS' and user defined alias into 'SELECT column' statement
     *
     * @param as alias name to be appended after 'AS'
     * @return AliasedColumn which has the same properties as Columns
     * except it cannot be aliased
     */
    public AliasedColumn alias(String as) {
        append(" AS ");
        append(as);
        return new AliasedColumn(this.queryString);
    }
}
