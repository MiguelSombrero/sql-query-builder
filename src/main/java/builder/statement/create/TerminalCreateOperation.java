package builder.statement.create;

import builder.Builder;
import builder.Query;

public class TerminalCreateOperation extends Query implements Builder {

    public TerminalCreateOperation(StringBuilder queryString) {
        super(queryString);
    }

    public String build() {
        append(")");
        return queryString.toString();
    }
}
