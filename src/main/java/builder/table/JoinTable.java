package builder.table;

import builder.SQLQueryBuilder;
import builder.clause.Condition;

public class JoinTable extends SQLQueryBuilder {

    public JoinTable(StringBuilder builder) {
        this.builder = builder;
    }

    public Condition where(String value) {
        append(" WHERE ");
        append(value);
        return new Condition(this.builder);
    }

    public On innerJoin(String table) {
        append(" INNER JOIN ");
        return join(table);
    }

    public On leftJoin(String table) {
        append(" LEFT JOIN ");
        return join(table);
    }

    private On join(String table) {
        append(table);
        return new On(this.builder);
    }
}
