package builder.statement.create;

enum DataType {
    INT("INT"),
    DOUBLE("DOUBLE"),
    TIMESTAMP("TIMESTAMP"),
    CHAR("CHAR"),
    VARCHAR_32("VARCHAR(32)"),
    VARCHAR_64("VARCHAR(64)"),
    VARCHAR_128("VARCHAR(128)"),
    VARCHAR_255("VARCHAR(255)");

    private String type;

    DataType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}