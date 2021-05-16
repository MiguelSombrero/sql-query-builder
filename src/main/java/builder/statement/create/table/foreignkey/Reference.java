package builder.statement.create.table.foreignkey;

import builder.SQLStringAppender;

import javax.xml.bind.ValidationException;

public class Reference extends SQLStringAppender {

    public Reference(StringBuilder queryString) {
        super(queryString);
    }

    public OnAction references(String column, String ofTable) throws ValidationException {
        append(" REFERENCES ");
        validateAndAppend(ofTable);
        append("(");
        validateAndAppend(column);
        append(")");
        return new OnAction(this.queryString);
    }
}
