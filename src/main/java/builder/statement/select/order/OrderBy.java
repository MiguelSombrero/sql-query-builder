package builder.statement.select.order;

import javax.xml.bind.ValidationException;

public class OrderBy extends Limit {

    public OrderBy(StringBuilder queryString) {
        super(queryString);
    }

    public Order column(String columnName) throws ValidationException {
        append(", ");
        validateAndAppend(columnName);
        return new Order(this.queryString);
    }
}
