package builder.statement.select.order;

import javax.xml.bind.ValidationException;

public class FirstOrderBy extends Limit {

    public FirstOrderBy(StringBuilder queryString) {
        super(queryString);
    }

    public Order column(String columnName) throws ValidationException {
        validateAndAppend(columnName);
        return new Order(this.queryString);
    }
}
