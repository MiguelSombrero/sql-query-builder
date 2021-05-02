package builder.statement.select.order;

public class Grouper extends Orderer {

    public Grouper(StringBuilder builder) {
        super(builder);
    }

    public FirstGroupBy groupBy() {
        append(" GROUP BY ");
        return new FirstGroupBy(this.builder);
    }
}
