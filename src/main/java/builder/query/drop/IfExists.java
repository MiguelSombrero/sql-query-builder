package builder.query.drop;

import query.Clause;

public class IfExists extends Name {

    public IfExists(Clause query) {
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
        clause.append("IF EXISTS ");
        return new Name(clause);
    }
}
