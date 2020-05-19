package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Field.getInstance().clear();
        Game game = new Game();
        game.start();
    }
}