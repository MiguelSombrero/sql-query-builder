package builder.statement.select.table;

import builder.SQLStringAppender;

public class AliasedOn extends SQLStringAppender {

    public AliasedOn(StringBuilder queryString) {
        super(queryString);
    }

    public JoinTable on(String condition) {
        append(" ON ");
        append(condition);
        return new JoinTable(this.queryString);
    }
}
