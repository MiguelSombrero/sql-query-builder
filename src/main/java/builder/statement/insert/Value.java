package builder.statement.insert;

public class Value extends ValueTemplate {

    public Value(StringBuilder queryString) {
        super(queryString);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        append(", ");
    }
}
