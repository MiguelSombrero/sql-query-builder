package builder.statement.create.table.foreignkey;

import builder.SQLStringBuilder;

public class Action extends SQLStringBuilder {

    public Action(StringBuilder builder) {
        super(builder);
    }

    public ForeignKey cascade() {
        append("CASCADE");
        return new ForeignKey(this.builder);
    }

    public ForeignKey restrict() {
        append("RESTRICT");
        return new ForeignKey(this.builder);
    }

    public ForeignKey setNull() {
        append("SET NULL");
        return new ForeignKey(this.builder);
    }

    public ForeignKey setDefault() {
        append("SET DEFAULT");
        return new ForeignKey(this.builder);
    }
}
