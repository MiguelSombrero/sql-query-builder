package builder.statement.create.table.foreignkey;

import builder.SQLStringAppender;

public class Action extends SQLStringAppender {

    public Action(StringBuilder queryString) {
        super(queryString);
    }

    public ForeignKey cascade() {
        append("CASCADE");
        return new ForeignKey(this.queryString);
    }

    public ForeignKey restrict() {
        append("RESTRICT");
        return new ForeignKey(this.queryString);
    }

    public ForeignKey setNull() {
        append("SET NULL");
        return new ForeignKey(this.queryString);
    }

    public ForeignKey setDefault() {
        append("SET DEFAULT");
        return new ForeignKey(this.queryString);
    }
}
