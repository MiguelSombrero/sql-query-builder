package builder.statement.select.table;

public class AliasedTable extends JoinTable {

    public AliasedTable(StringBuilder queryString) {
        super(queryString);
    }

    public Table table(String table) {
        validator.validate(table);
        append(", ");
        append(table);
        return new Table(this.queryString);
    }
}
