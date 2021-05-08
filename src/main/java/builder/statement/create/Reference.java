package builder.statement.create;

import builder.SQLStringBuilder;

public class Reference extends SQLStringBuilder {

    public Reference(StringBuilder builder) {
        this.builder = builder;
    }

    public ForeignKey references(String column, String ofTable) {
        int index = lastIndexOfRightBracket();

        insert(index, ")");
        insert(index, column);
        insert(index, "(");
        insert(index, ofTable);
        insert(index, " REFERENCES ");
        return new ForeignKey(this.builder);
    }
}
