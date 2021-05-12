package builder.statement.select.column;

import builder.SQLStringAppender;

public abstract class ColumnTemplate extends SQLStringAppender {

    public ColumnTemplate(StringBuilder queryString) {
        super(queryString);
    }

    public Column column(String fieldName) {
        addCommaAfterFirstValue();
        append(fieldName);
        return new Column(this.queryString);
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
        return new Column(this.queryString);
    }

    protected abstract void addCommaAfterFirstValue();
}
