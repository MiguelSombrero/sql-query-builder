package builder.statement.create;

public class Column extends ForeignKey {

    public Column(StringBuilder builder) {
        super(builder);
    }

    public ColumnType column(String columns) {
        int index = lastIndexOfRightBracket();
        insert(index, columns);
        insert(index, ", ");
        return new ColumnType(this.builder);
    }
}
