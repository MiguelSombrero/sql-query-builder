package builder.statement.select.order;

public class Orderer extends Limit {

    public Orderer(StringBuilder queryString) {
        super(queryString);
    }

    public FirstOrderBy orderBy() {
        append(" ORDER BY ");
        return new FirstOrderBy(this.queryString);
    }
}
