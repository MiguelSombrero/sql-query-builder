package builder.statement.insert;

public class FirstValue extends ValueTemplate {

    public FirstValue(StringBuilder queryString) {
        super(queryString);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
