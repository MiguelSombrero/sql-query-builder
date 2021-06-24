package builder.query.drop;

import query.Clause;

public class Drop {
    private Clause clause;

    public Drop(Clause clause) {
        this.clause = clause;
    }

    /**
     * Appends 'TABLE' into 'DROP TABLE name'
     * statement.
     *
     * @return IfExists class which can be used
     * to append table name and IF EXISTS clause
     * to DROP query.
     */
    public IfExists table() {
        clause.append("TABLE ");
        return new IfExists(clause);
    }

    /**
     * Appends 'DATABASE' into 'DROP DATABASE name'
     * statement.
     *
     * @return IfExists class which can be used
     * to append database name and IF EXISTS clause
     * to DROP query.
     */
    public IfExists database() {
        clause.append("DATABASE ");
        return new IfExists(clause);
    }
}
