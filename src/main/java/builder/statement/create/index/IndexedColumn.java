package builder.statement.create.index;

import builder.SQLStringBuilder;
import builder.TerminalOperation;
import builder.statement.create.TerminalCreateOperation;

public class IndexedColumn extends SQLStringBuilder {

    public IndexedColumn(StringBuilder builder) {
        super(builder);
    }

    public TerminalCreateOperation columns(String ...listOfColumns) {
        append(" (");
        append(listOfColumns[0]);

        for (int i = 1; i < listOfColumns.length; i++) {
            append(", ");
            append(listOfColumns[i]);
        }

        return new TerminalCreateOperation(this.builder);
    }
}
