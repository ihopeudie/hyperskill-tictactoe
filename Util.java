package tictactoe;

public class Util {

    public static final String ERROR_COORDS_INPUT = "You should enter numbers!";
    private static final String ERROR_COORDS_RANGE = "Coordinates should be from 1 to " + Field.FIELD_SIZE + "!";

    public static String getGameStateString(GameState state) {
        switch (state) {
            case DRAW:
                return "Draw";
            case CROSS_WINS:
                return "X wins";
            case ZERO_WINS:
                return "O wins";
            case IMPOSSIBLE:
                return "Impossible";
            default:
                return "Game not finished";
        }
    }

    public static int[] getTwoNumbers(String input) {
        String[] split = input.split(" ");
        if (split.length != 2) {
            System.out.println(ERROR_COORDS_INPUT);
            return new int[0];
        }
        try {
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            if (x < 1 || x > Field.FIELD_SIZE || y < 1 || y > Field.FIELD_SIZE) {
                System.out.println(ERROR_COORDS_RANGE);
                return new int[0];
            }
            return new int[]{x, y};
        } catch (NumberFormatException e) {
            System.out.println(ERROR_COORDS_INPUT);
            return new int[0];
        }
    }
}