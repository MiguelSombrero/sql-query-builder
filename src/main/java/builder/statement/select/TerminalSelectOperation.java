package builder.statement.select;

import query.SelectQuery;

public class TerminalSelectOperation implements SelectQueryBuilder {

    protected SelectQuery query;

    /**
     * Terminates query building.
     *
     * @return String presentation of query
     */
    public TerminalSelectOperation(SelectQuery query) {
        this.query = query;
    }

    public String build() {
        return query.build();
    }

}
