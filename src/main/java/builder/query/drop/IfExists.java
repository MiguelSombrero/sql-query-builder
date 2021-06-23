package builder.query.drop;

import query.DropQuery;

public class IfExists extends Name {

    public IfExists(DropQuery query) {
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
        query.append("IF EXISTS ");
        return new Name(query);
    }
}
