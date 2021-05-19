package builder.statement.create.table.foreignkey;

import builder.query.Query;

public class Action {

    private Query query;

    public Action(Query query) {
        this.query = query;
    }

    public ForeignKey cascade() {
        query.append("CASCADE");
        return new ForeignKey(query);
    }

    public ForeignKey restrict() {
        query.append("RESTRICT");
        return new ForeignKey(query);
    }

    public ForeignKey setNull() {
        query.append("SET NULL");
        return new ForeignKey(query);
    }

    public ForeignKey setDefault() {
        query.append("SET DEFAULT");
        return new ForeignKey(query);
    }
}
