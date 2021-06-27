package builder.statement;

import query.Statement;

public class TerminalStatementOperation implements StatementBuilder {

    protected Statement statement;

    public TerminalStatementOperation(Statement statement) {
        this.statement = statement;
    }

    /**
     * Terminates clause building.
     *
     * @return Clause object which can be used to
     * embed it to SQL queries
     */
    public Statement build() {
        return this.statement;
    }
}
