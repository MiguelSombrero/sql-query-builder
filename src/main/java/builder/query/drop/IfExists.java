package builder.query.drop;

import query.Statement;

public class IfExists extends Name {

    public IfExists(Statement query) {
        super(query);
    }

    /**
     * Appends 'IF EXISTS' into 'DROP TABLE table IF EXISTS'
     * statement.
     *
     * @return Name class which can be used
     * to append 'name' in DROP query
     */
    public Name ifExists() {
        statement.append("IF EXISTS ");
        return new Name(statement);
    }
}
