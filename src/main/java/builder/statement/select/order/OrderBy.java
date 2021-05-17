package builder.statement.select.order;

public class OrderBy extends Limit {

    public OrderBy(StringBuilder queryString) {
        super(queryString);
    }

    public Order column(String columnName) {
        append(", ");
        validateAndAppend(columnName);
        return new Order(this.queryString);
    }
}
