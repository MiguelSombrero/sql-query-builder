package builder.statement.insert;

import builder.SQLStringBuilder;
import builder.TerminalOperation;
import builder.statement.select.SelectBuilder;

public class Column extends SQLStringBuilder {

    public Column(StringBuilder builder) {
        this.builder = builder;
    }

    public FirstValue values() {
        append("VALUES ()");
        return new FirstValue(this.builder);
    }

    public TerminalOperation sub(SelectBuilder query) {
        append(query.build());
        return new TerminalOperation(this.builder);
    }
}
