package builder.select.order;

import builder.TerminalOperation;

public class Grouper extends TerminalOperation {

    public Grouper(StringBuilder builder) {
        this.builder = builder;
    }

    public FirstGroupBy groupBy() {
        append(" GROUP BY ");
        return new FirstGroupBy(this.builder);
    }
}
