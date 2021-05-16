package builder.statement.select.table;

import builder.SQLStringAppender;

import javax.xml.bind.ValidationException;

public class AliasedOn extends SQLStringAppender {

    public AliasedOn(StringBuilder queryString) {
        super(queryString);
    }

    public JoinTable on(String column, String joinColumn) throws ValidationException {
        append(" ON ");
        validateAndAppend(column);
        append(" = ");
        validateAndAppend(joinColumn);
        return new JoinTable(this.queryString);
    }
}
