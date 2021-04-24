package builder.select.table;

import builder.SQLQueryBuilder;
import builder.select.conjunction.Negation;

public class JoinTable extends SQLQueryBuilder {
    private On on;

    public JoinTable(StringBuilder builder) {
        this.builder = builder;
        this.on = new On(this);
    }

    public Negation where(String fieldValue) {
        append(" WHERE ");
        append(fieldValue);
        return new Negation(this.builder);
    }

    public On innerJoin(String table) {
        append(" INNER JOIN ");
        return join(table);
    }

    public On leftJoin(String table) {
        append(" LEFT JOIN ");
        return join(table);
    }

    public On rightJoin(String table) {
        append(" RIGHT JOIN ");
        return join(table);
    }

    private On join(String table) {
        append(table);
        return on;
    }
}
