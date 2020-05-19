package tictactoe;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

public class Field {

    private static final String ERROR_CELL_OCCUPIED = "This cell is occupied! Choose another one!";
    private static Field instance;
    private final FieldValue[][] field;
    public static final int FIELD_SIZE = 3;

    private Field() {
        field = new FieldValue[FIELD_SIZE][FIELD_SIZE];
    }

    public static Field getInstance() {
        if (instance == null) {
            instance = new Field();
        }
        return instance;
    }

    private void setMove(int x, int y, FieldValue value) {
        field[x][y] = value;
    }

    void fillRandom() {
        Random random = new Random(new Date().getTime());
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                setMove(i, j, random.nextBoolean() ? FieldValue.CROSS : FieldValue.ZERO);
            }
        }
    }

    void clear() {
        for (int i = 0; i < field.length; i++) {
            field[i] = new FieldValue[]{FieldValue.EMPTY, FieldValue.EMPTY, FieldValue.EMPTY};
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String line = "-".repeat(Math.max(0, field.length + (field.length + 1)));
        sb.append(line);
        sb.append("\r\n");
        for (FieldValue[] row : field) {
            sb.append("| ");
            sb.append(Arrays.stream(row).map(FieldValue::getValue).collect(Collectors.joining(" ")));
            sb.append("| ");
            sb.append("\r\n");
        }
        sb.append(line);
        return sb.toString();
    }

    public void importField(String importString) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < importString.length(); i++) {
            String value = String.valueOf(importString.charAt(i));
            setMove(x, y, FieldValue.getEnumByValue(value));
            if (++y > field.length - 1) {
                y = 0;
                x++;
            }
        }
    }

    public FieldValue[][] getField() {
        return field.clone();
    }

    public boolean move(int x, int y, FieldValue symbol) {
        FieldValue cellValue = field[FIELD_SIZE - y][x - 1];
        if (cellValue == FieldValue.EMPTY) {
            setMove(FIELD_SIZE - y, x - 1, symbol);
            return true;
        }
        System.out.println(ERROR_CELL_OCCUPIED);
        return false;
    }
}