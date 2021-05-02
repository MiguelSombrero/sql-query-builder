package builder.statement.select.order;

public class Having extends Orderer {

    public Having(StringBuilder builder) {
        super(builder);
    }

    public Having column(String columnName) {
        append(", ");
        append(columnName);
        return this;
    }

    public Orderer having(String condition) {
        append(" HAVING ");
        append(condition);
        return new Orderer(this.builder);
    }
}
