package builder.select.order;

import builder.TerminalOperation;

public class Orderer extends TerminalOperation {

    public Orderer(StringBuilder builder) {
        this.builder = builder;
    }

    public FirstOrderBy orderBy() {
        append(" ORDER BY ");
        return new FirstOrderBy(this.builder);
    }
}
