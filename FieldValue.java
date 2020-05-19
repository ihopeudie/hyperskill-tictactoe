package tictactoe;

public enum FieldValue {

    CROSS("X"), ZERO("O"), EMPTY("_");

    private final String value;

    FieldValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FieldValue getEnumByValue(String value) {
        switch (value) {
            case "X":
                return FieldValue.CROSS;
            case "O":
                return FieldValue.ZERO;
            case "_":
                return FieldValue.EMPTY;
            default:
                throw new IllegalArgumentException("Cant find value for FieldValue");
        }
    }
}