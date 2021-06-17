package builder.clause;

import clause.Clause;

public class TerminalClauseOperation implements ClauseBuilder {

    protected Clause clause;

    public TerminalClauseOperation(Clause clause) {
        this.clause = clause;
    }

    /**
     * Terminates clause building.
     *
     * @return Clause object which can be used to
     * embed it to SQL queries
     */
    public Clause build() {
        return this.clause;
    }
}
