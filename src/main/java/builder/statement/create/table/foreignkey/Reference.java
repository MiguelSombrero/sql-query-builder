package builder.statement.create.table.foreignkey;

import builder.SQLStringBuilder;

public class Reference extends SQLStringBuilder {

    public Reference(StringBuilder builder) {
        super(builder);
    }

    public OnAction references(String column, String ofTable) {
        append(" REFERENCES ");
        append(ofTable);
        append("(");
        append(column);
        append(")");
        return new OnAction(this.builder);
    }
}
