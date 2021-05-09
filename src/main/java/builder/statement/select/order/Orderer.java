package builder.statement.select.order;

public class Orderer extends Limit {

    public Orderer(StringBuilder builder) {
        super(builder);
    }

    public FirstOrderBy orderBy() {
        append(" ORDER BY ");
        return new FirstOrderBy(this.builder);
    }
}
