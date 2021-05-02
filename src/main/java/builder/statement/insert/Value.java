package builder.statement.insert;

public class Value extends ValueTemplate {

    public Value(StringBuilder builder) {
        super(builder);
    }

    @Override
    protected void addCommaAfterFirstValue(int index) {
        insert(index, ", ");
    }
}
