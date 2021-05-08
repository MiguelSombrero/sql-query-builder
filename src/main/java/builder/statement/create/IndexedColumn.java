package builder.statement.create;

import builder.SQLStringBuilder;
import builder.TerminalOperation;

public class IndexedColumn extends SQLStringBuilder {

    public IndexedColumn(StringBuilder builder) {
        this.builder = builder;
    }

    public TerminalOperation columns(String ...listOfColumns) {
        append(" (");
        append(listOfColumns[0]);

        for (int i = 1; i < listOfColumns.length; i++) {
            append(", ");
            append(listOfColumns[i]);
        }

        append(")");
        return new TerminalOperation(this.builder);
    }
}
