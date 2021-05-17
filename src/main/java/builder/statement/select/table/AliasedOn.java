package builder.statement.select.table;

import builder.SQLStringAppender;

public class AliasedOn extends SQLStringAppender {

    public AliasedOn(StringBuilder queryString) {
        super(queryString);
    }

    public JoinTable on(String column, String joinColumn) {
        append(" ON ");
        validateAndAppend(column);
        append(" = ");
        validateAndAppend(joinColumn);
        return new JoinTable(this.queryString);
    }
}
