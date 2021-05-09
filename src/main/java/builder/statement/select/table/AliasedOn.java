package builder.statement.select.table;

import builder.SQLStringBuilder;

public class AliasedOn extends SQLStringBuilder {

    public AliasedOn(StringBuilder builder) {
        super(builder);
    }

    public JoinTable on(String condition) {
        append(" ON ");
        append(condition);
        return new JoinTable(this.builder);
    }
}
