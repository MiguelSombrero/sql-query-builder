package builder.statement.insert;

import builder.SQLStringBuilder;
import builder.TerminalOperation;

public class Column extends SQLStringBuilder {

    public Column(StringBuilder builder) {
        this.builder = builder;
    }

    public FirstValue values() {
        append("VALUES ()");
        return new FirstValue(this.builder);
    }

    public TerminalOperation sub(String query) {
        append(query);
        return new TerminalOperation(this.builder);
    }
}
