package builder.statement.create.index;

import builder.SQLStringAppender;

import javax.xml.bind.ValidationException;

public class Index extends SQLStringAppender {

    public Index(StringBuilder queryString) {
        super(queryString);
    }

    public IndexedColumn on(String table) throws ValidationException {
        append(" ON ");
        validateAndAppend(table);
        return new IndexedColumn(this.queryString);
    }
}
