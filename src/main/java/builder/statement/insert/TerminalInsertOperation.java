package builder.statement.insert;

import builder.Builder;
import builder.SQLStringAppender;

public class TerminalInsertOperation extends SQLStringAppender implements Builder {

    public TerminalInsertOperation(StringBuilder queryString) {
        super(queryString);
    }

    public String build() {
        append(")");
        return queryString.toString();
    }
}
