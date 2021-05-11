package builder.statement.create;

import builder.Builder;
import builder.SQLStringBuilder;

public class TerminalCreateOperation extends SQLStringBuilder implements Builder {

    public TerminalCreateOperation(StringBuilder builder) {
        super(builder);
    }

    public String build() {
        append(")");
        return builder.toString();
    }
}
