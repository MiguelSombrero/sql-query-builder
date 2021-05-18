package builder.statement.create;

import builder.Builder;
import builder.SQLQuery;

public class TerminalCreateOperation extends SQLQuery implements Builder {

    public TerminalCreateOperation(StringBuilder queryString) {
        super(queryString);
    }

    public String build() {
        append(")");
        return queryString.toString();
    }
}
