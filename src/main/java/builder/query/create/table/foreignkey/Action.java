package builder.query.create.table.foreignkey;

import query.Statement;

public class Action {
    private Statement statement;

    public Action(Statement statement) {
        this.statement = statement;
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
        statement.append("CASCADE");
        return new ForeignKey(statement);
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
        statement.append("RESTRICT");
        return new ForeignKey(statement);
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
        statement.append("SET NULL");
        return new ForeignKey(statement);
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
        statement.append("SET DEFAULT");
        return new ForeignKey(statement);
    }
}
