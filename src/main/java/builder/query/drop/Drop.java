package builder.query.drop;

import query.Statement;

public class Drop {
    private Statement statement;

    public Drop(Statement statement) {
        this.statement = statement;
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
        statement.append("TABLE ");
        return new IfExists(statement);
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
        statement.append("DATABASE ");
        return new IfExists(statement);
    }
}
