package builder.statement.create;

public class Column extends ForeignKey {

    public Column(StringBuilder builder) {
        super(builder);
    }

    public ColumnType column(String column) {
        int index = lastIndexOfRightBracket();
        insert(index, column);
        insert(index, ", ");
        return new ColumnType(this.builder);
    }
}
