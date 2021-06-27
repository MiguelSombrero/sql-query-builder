package builder.query.select.order;

import query.Statement;

public class Orderer extends Limit {

    public Orderer(Statement statement) {
        super(statement);
    }

    /**
     * Appends 'ORDER BY' into query string 'SELECT ... ORDER BY column(s)'.
     *
     * @return FirstOrderBy class which can be used to
     * append 'column(s)' in ORDER BY clause
     */
    public FirstOrderBy orderBy() {
        statement.append(" ORDER BY ");
        return new FirstOrderBy(statement);
    }
}
