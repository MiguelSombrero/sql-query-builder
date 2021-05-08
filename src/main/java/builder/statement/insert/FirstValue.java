package builder.statement.insert;

public class FirstValue extends ValueTemplate {

    public FirstValue(StringBuilder builder) {
        super(builder);
    }

    @Override
    protected void addCommaAfterFirstValue(int index) {
    }
}
