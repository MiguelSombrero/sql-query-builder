package builder.select.table;

import builder.TerminalOperation;
import builder.select.conjunction.Negation;
import builder.select.order.FirstGroupBy;
import builder.select.order.FirstOrderBy;

public class JoinTable extends TerminalOperation {

    public JoinTable(StringBuilder builder) {
        this.builder = builder;
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

    public FirstGroupBy groupBy() {
        append(" GROUP BY ");
        return new FirstGroupBy(this.builder);
    }

    public FirstOrderBy orderBy() {
        append(" ORDER BY ");
        return new FirstOrderBy(this.builder);
    }

    private On join(String table) {
        append(table);
        return new On(this.builder);
    }
}
