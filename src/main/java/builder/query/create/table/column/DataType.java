package builder.query.create.table.column;

public enum DataType {
    INT("INT"),
    BIGINT("BIGINT"),
    DOUBLE("DOUBLE"),
    DATE("DATE"),
    TIMESTAMP("TIMESTAMP"),
    BOOLEAN("BOOLEAN"),
    CHAR_32("CHAR(32)"),
    CHAR_64("CHAR(64)"),
    CHAR_128("CHAR(128)"),
    CHAR_255("CHAR(255)"),
    VARCHAR_32("VARCHAR(32)"),
    VARCHAR_64("VARCHAR(64)"),
    VARCHAR_128("VARCHAR(128)"),
    VARCHAR_255("VARCHAR(255)"),
    BLOB("BLOB");

    private String type;

    DataType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}