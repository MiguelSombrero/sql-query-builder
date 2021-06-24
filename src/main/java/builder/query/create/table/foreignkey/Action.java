package builder.query.create.table.foreignkey;

import query.Clause;

public class Action {
    private Clause clause;

    public Action(Clause clause) {
        this.clause = clause;
    }

    /**
     * Appends 'CASCADE' into 'FOREIGN KEY (column)
     * REFERENCES Table(column) ON action CASCADE' statement.
     *
     * @return ForeignKey class which can be used to create
     * another foreign key constraint or terminate
     * query building
     */
    public ForeignKey cascade() {
        clause.append("CASCADE");
        return new ForeignKey(clause);
    }

    /**
     * Appends 'RESTRICT' into 'FOREIGN KEY (column)
     * REFERENCES Table(column) ON action RESTRICT' statement.
     *
     * @return ForeignKey class which can be used to create
     * another foreign key constraint or terminate
     * query building
     */
    public ForeignKey restrict() {
        clause.append("RESTRICT");
        return new ForeignKey(clause);
    }

    /**
     * Appends 'SET NULL' into 'FOREIGN KEY (column)
     * REFERENCES Table(column) ON action SET NULL' statement.
     *
     * @return ForeignKey class which can be used to create
     * another foreign key constraint or terminate
     * query building
     */
    public ForeignKey setNull() {
        clause.append("SET NULL");
        return new ForeignKey(clause);
    }

    /**
     * Appends 'SET DEFAULT' into 'FOREIGN KEY (column)
     * REFERENCES Table(column) ON action SET DEFAULT' statement.
     *
     * @return ForeignKey class which can be used to create
     * another foreign key constraint or terminate
     * query building
     */
    public ForeignKey setDefault() {
        clause.append("SET DEFAULT");
        return new ForeignKey(clause);
    }
}
