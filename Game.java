package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Game {

    private FieldValue[][] field;

    public Game() {
        this.field = Field.getInstance().getField();
    }

    public void doMove(FieldValue symbol) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(Field.getInstance().toString());
            int[] move;
            do {
                System.out.println("Enter the coordinates:");
                String line = scanner.nextLine();
                move = Util.getTwoNumbers(line);
            }
            while (move.length < 2 || !Field.getInstance().move(move[0], move[1], symbol));
            System.out.println(Field.getInstance().toString());
        }

    }

    public GameState checkState() {
        this.field = Field.getInstance().getField();
        if (isImpossible()) {
            return GameState.IMPOSSIBLE;
        }
        if (crossWins()) {
            return GameState.CROSS_WINS;
        }
        if (zeroWins()) {
            return GameState.ZERO_WINS;
        }
        if (noMoreMoves()) {
            return GameState.DRAW;
        }
        return GameState.NOT_FINISHED;
    }

    private boolean noMoreMoves() {
        return getRows()
                .stream()
                .noneMatch(row -> row
                        .stream()
                        .anyMatch(el -> el == FieldValue.EMPTY)
                );
    }

    private boolean isImpossible() {
        return (bothPlayersWin() || wrongAmounts());
    }

    private boolean wrongAmounts() {
        int xCounter = 0;
        int zeroCounter = 0;
        for (FieldValue[] row : field) {
            for (FieldValue cell : row) {
                if (cell == FieldValue.CROSS) {
                    xCounter++;
                }
                if (cell == FieldValue.ZERO) {
                    zeroCounter++;
                }
            }
        }
        return Math.abs(xCounter - zeroCounter) > 1;
    }

    private boolean crossWins() {
        return hasWinConditions(FieldValue.CROSS);
    }

    private boolean zeroWins() {
        return hasWinConditions(FieldValue.ZERO);
    }

    private boolean hasWinConditions(FieldValue val) {
        var rows = getRows();
        var cols = getColumns();
        var diags = getDiags();
        return Stream
                .concat(diags.stream(), Stream.concat(rows.stream(), cols.stream()))
                .anyMatch(seq -> seq.stream().allMatch(el -> el == val));
    }

    private List<List<FieldValue>> getRows() {
        List<List<FieldValue>> list = new ArrayList<>();
        for (FieldValue[] fieldValues : field) {
            list.add(List.of(fieldValues));
        }
        return list;
    }

    private List<List<FieldValue>> getColumns() {
        List<List<FieldValue>> list = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            List<FieldValue> col = new ArrayList<>();
            for (int j = 0; j < field[0].length; j++) {
                col.add(field[j][i]);
            }
            list.add(col);
        }
        return list;
    }

    private List<List<FieldValue>> getDiags() {
        List<List<FieldValue>> list = new ArrayList<>();
        List<FieldValue> mainDiag = new ArrayList<>();
        List<FieldValue> secondDiag = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            mainDiag.add(field[i][i]);
            secondDiag.add(field[field.length - i - 1][i]);
        }
        list.add(mainDiag);
        list.add(secondDiag);
        return list;
    }

    private boolean bothPlayersWin() {
        return crossWins() && zeroWins();
    }

    public void start() {
        int i = 0;
        GameState gameState;
        do {
            doMove(i % 2 == 0 ? FieldValue.CROSS : FieldValue.ZERO);
            i++;
            gameState = checkState();
        }
        while (gameState == GameState.NOT_FINISHED);
        System.out.println(Util.getGameStateString(gameState));
    }
}