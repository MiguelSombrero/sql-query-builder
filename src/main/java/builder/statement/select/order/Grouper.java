package builder.statement.select.order;

public class Grouper extends Orderer {

    public Grouper(StringBuilder builder) {
        super(builder);
    }

    public GroupBy groupBy() {
        append(" GROUP BY ");
        return new GroupBy(this.builder);
    }
}
