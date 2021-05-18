package builder.statement.insert;

import builder.Builder;
import builder.SQLQuery;

public class TerminalInsertOperation extends SQLQuery implements Builder {

    public TerminalInsertOperation(StringBuilder queryString) {
        super(queryString);
    }

    public String build() {
        append(")");
        return queryString.toString();
    }
}
