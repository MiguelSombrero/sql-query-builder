package builder.statement.select.column;

import builder.SQLStringAppender;

/**
 * Defines the methods for SELECTing columns or aggregate functions
 */
public abstract class ColumnTemplate extends SQLStringAppender {

    public ColumnTemplate(StringBuilder queryString) {
        super(queryString);
    }

    /**
     *
     * @param column Column name to be queried by SELECT
     * @return Column class 
     */
    public Column column(String column) {
        addCommaAfterFirstValue();
        append(column);
        return new Column(this.queryString);
    }

    public Column count(String column) {
        return applyAggregate("COUNT", column);
    }

    public Column min(String column) {
        return applyAggregate("MIN", column);
    }

    public Column max(String column) {
        return applyAggregate("MAX", column);
    }

    public Column avg(String column) {
        return applyAggregate("AVG", column);
    }

    public Column sum(String column) {
        return applyAggregate("SUM", column);
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
