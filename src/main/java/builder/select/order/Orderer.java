package builder.select.order;

public class Orderer extends Grouper {

    public Orderer(StringBuilder builder) {
        super(builder);
    }

    public FirstOrderBy orderBy() {
        append(" ORDER BY ");
        return new FirstOrderBy(this.builder);
    }
}
