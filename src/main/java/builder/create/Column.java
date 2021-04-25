package builder.create;

public class Column extends ColumnTemplate {

    public Column(StringBuilder builder) {
        super(builder);
    }

    @Override
    protected void addCommaAfterFirstValue(int index) {
        insert(index, ", ");
    }
}
