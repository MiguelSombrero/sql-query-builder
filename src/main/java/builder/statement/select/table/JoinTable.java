package builder.statement.select.table;

import builder.statement.select.condition.Negation;
import builder.statement.select.order.Grouper;

public class JoinTable extends Grouper {

    public JoinTable(StringBuilder builder) {
        super(builder);
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
        return new On(this.builder);
    }
}
