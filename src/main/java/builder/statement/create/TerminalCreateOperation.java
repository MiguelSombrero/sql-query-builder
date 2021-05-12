package builder.statement.create;

import builder.Builder;
import builder.SQLStringAppender;

public class TerminalCreateOperation extends SQLStringAppender implements Builder {

    public TerminalCreateOperation(StringBuilder queryString) {
        super(queryString);
    }

    public String build() {
        append(")");
        return queryString.toString();
    }
}
