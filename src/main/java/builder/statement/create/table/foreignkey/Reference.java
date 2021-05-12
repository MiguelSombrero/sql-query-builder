package builder.statement.create.table.foreignkey;

import builder.SQLStringAppender;

public class Reference extends SQLStringAppender {

    public Reference(StringBuilder queryString) {
        super(queryString);
    }

    public OnAction references(String column, String ofTable) {
        append(" REFERENCES ");
        append(ofTable);
        append("(");
        append(column);
        append(")");
        return new OnAction(this.queryString);
    }
}
