package builder.statement.select.column;

import builder.SQLStringBuilder;

public abstract class ColumnTemplate extends SQLStringBuilder {

    public ColumnTemplate(StringBuilder builder) {
        super(builder);
    }

    public Column column(String fieldName) {
        addCommaAfterFirstValue();
        append(fieldName);
        return new Column(this.builder);
    }

    public Column count(String columnName) {
        return applyAggregate("COUNT", columnName);
    }

    public Column min(String columnName) {
        return applyAggregate("MIN", columnName);
    }

    public Column max(String columnName) {
        return applyAggregate("MAX", columnName);
    }

    public Column avg(String columnName) {
        return applyAggregate("AVG", columnName);
    }

    public Column sum(String columnName) {
        return applyAggregate("SUM", columnName);
    }

    private Column applyAggregate(String function, String toColumn) {
        addCommaAfterFirstValue();
        append(function);
        append("(");
        append(toColumn);
        append(")");
        return new Column(this.builder);
    }

    protected abstract void addCommaAfterFirstValue();
}
