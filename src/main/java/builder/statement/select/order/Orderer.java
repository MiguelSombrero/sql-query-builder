package builder.statement.select.order;

public class Orderer extends Limit {

    public Orderer(StringBuilder builder) {
        super(builder);
    }

    public OrderBy orderBy() {
        append(" ORDER BY ");
        return new OrderBy(this.builder);
    }
}
