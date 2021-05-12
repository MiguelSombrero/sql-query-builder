package builder.statement.select.order;

public class Grouper extends Orderer {

    public Grouper(StringBuilder queryString) {
        super(queryString);
    }

    public GroupBy groupBy() {
        append(" GROUP BY ");
        return new GroupBy(this.queryString);
    }
}
