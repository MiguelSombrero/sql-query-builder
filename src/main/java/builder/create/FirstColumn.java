package builder.create;

public class FirstColumn extends ColumnTemplate {

    public FirstColumn(StringBuilder builder) {
        super(builder);
    }

    @Override
    protected void addCommaAfterFirstValue(int index) {
    }
}
