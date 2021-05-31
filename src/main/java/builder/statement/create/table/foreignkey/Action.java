package builder.statement.create.table.foreignkey;

import query.SQLQuery;

public class Action {

    private SQLQuery SQLQuery;

    public Action(SQLQuery SQLQuery) {
        this.SQLQuery = SQLQuery;
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
        SQLQuery.append("CASCADE");
        return new ForeignKey(SQLQuery);
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
        SQLQuery.append("RESTRICT");
        return new ForeignKey(SQLQuery);
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
        SQLQuery.append("SET NULL");
        return new ForeignKey(SQLQuery);
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
        SQLQuery.append("SET DEFAULT");
        return new ForeignKey(SQLQuery);
    }
}
