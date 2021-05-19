package builder.statement.select.order;

import builder.Query;

public class Orderer extends Limit {

    public Orderer(Query query) {
        super(query);
    }

    public FirstOrderBy orderBy() {
        query.append(" ORDER BY ");
        return new FirstOrderBy(query);
    }
}
