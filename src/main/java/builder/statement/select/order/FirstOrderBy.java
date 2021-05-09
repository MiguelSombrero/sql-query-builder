package builder.statement.select.order;

public class FirstOrderBy extends Limit {

    public FirstOrderBy(StringBuilder builder) {
        super(builder);
    }

    public Order column(String columnName) {
        append(columnName);
        return new Order(this.builder);
    }
}
