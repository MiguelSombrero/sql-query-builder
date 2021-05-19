package builder.statement.select.order;

import builder.Query;

public class Grouper extends Orderer {

    public Grouper(Query query) {
        super(query);
    }

    public GroupBy groupBy() {
        query.append(" GROUP BY ");
        return new GroupBy(query);
    }
}
