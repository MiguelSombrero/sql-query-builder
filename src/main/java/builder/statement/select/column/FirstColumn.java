package builder.statement.select.column;

public class FirstColumn extends ColumnTemplate {

    public FirstColumn(StringBuilder queryString) {
        super(queryString);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
