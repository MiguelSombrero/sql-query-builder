package builder.query.drop;

import query.DropQuery;

public class Drop {
    private DropQuery query;

    public Drop(DropQuery query) {
        this.query = query;
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
        query.append("TABLE ");
        return new IfExists(query);
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
        query.append("DATABASE ");
        return new IfExists(query);
    }
}
