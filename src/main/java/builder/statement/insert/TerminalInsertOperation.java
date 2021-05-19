package builder.statement.insert;

import builder.Builder;
import builder.Query;

public class TerminalInsertOperation extends Query implements Builder {

    public TerminalInsertOperation(StringBuilder queryString) {
        super(queryString);
    }

    public String build() {
        append(")");
        return queryString.toString();
    }
}
