package builder.select.table;

import builder.SQLStringBuilder;

public class On extends SQLStringBuilder {

    public On(StringBuilder builder) {
        this.builder = builder;
    }

    public JoinTable on(String condition) {
        append(" ON ");
        append(condition);
        return new JoinTable(this.builder);
    }
}
