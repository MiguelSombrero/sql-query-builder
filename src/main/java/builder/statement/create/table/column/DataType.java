package builder.statement.create.table.column;

public enum DataType {
    INT("INT"),
    BIGINT("BIGINT"),
    DOUBLE("DOUBLE"),
    DATE("DATE"),
    DATETIME("DATETIME"),
    TIMESTAMP("TIMESTAMP"),
    BOOLEAN("BOOLEAN"),
    CHAR("CHAR"),
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