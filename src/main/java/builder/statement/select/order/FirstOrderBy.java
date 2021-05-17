package builder.statement.select.order;

public class FirstOrderBy extends Limit {

    public FirstOrderBy(StringBuilder queryString) {
        super(queryString);
    }

    public Order column(String columnName) {
        validateAndAppend(columnName);
        return new Order(this.queryString);
    }
}
