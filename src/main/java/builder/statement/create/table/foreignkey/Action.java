package builder.statement.create.table.foreignkey;

import query.ddl.DDLQuery;

public class Action {

    private DDLQuery query;

    public Action(DDLQuery query) {
        this.query = query;
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
        query.append("CASCADE");
        return new ForeignKey(query);
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
        query.append("RESTRICT");
        return new ForeignKey(query);
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
        query.append("SET NULL");
        return new ForeignKey(query);
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
        query.append("SET DEFAULT");
        return new ForeignKey(query);
    }
}
