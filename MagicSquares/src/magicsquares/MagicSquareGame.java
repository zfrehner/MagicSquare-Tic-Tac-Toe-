package magicsquares;

import java.util.Scanner;

/**
 *
 * @author Zach Frehner
 * @version 1.0
 */
public class MagicSquareGame {

    private String player1;
    private String player2;

    private static final Scanner input = new Scanner(System.in);
    private static final MagicSquares player1Square = new MagicSquares();
    private static final MagicSquares player2Square = new MagicSquares();
    private static int draw = 511;
    private static boolean keepPlaying = true;

    /**
     * This main method setups the game play and contains the game
     * loop where each player continues to take turns until
     * a player wins or the game is a DRAW
     * @param args not used
     */
    public static void main(String[] args) {
        printDirections();
        //Scanner input = new Scanner(System.in);

        System.out.println("Enter a name for player #1");

        String player1 = input.nextLine();
        //System.out.println(player1);

        System.out.println("Enter a name for player #2");
        String player2 = input.nextLine();
        //System.out.println(player2);


            while(keepPlaying) {
                int player1Selection = getSelection(player1, player1Square, player2Square);
                System.out.println(takePlayerTurn(player1Selection, player1Square, player1));
                if(!keepPlaying) {
                    break;
                }
                int player2Selection = getSelection(player2, player2Square, player1Square);
                System.out.println(takePlayerTurn(player2Selection, player2Square, player2));

            }

    }


    /**
     * This takePlayerTurn method will prompt for and display the player's
     * choice of numbers and then determine if the game has been won,
     * is a draw, or the game should continue
     *
     * @param selection - the Players selection
     * @param playerSquare - player1 and player 2 choices
     * @param name players name
     * @return a number indicating the game state(CONTINUE, DRAW, WIN)
     */
    public static String takePlayerTurn(int selection, MagicSquares playerSquare, String name) {
        playerSquare.choose((byte) selection);
        playerSquare.printChoices();
        if(isWin(playerSquare)) {
            keepPlaying = false;
            return name + " wins!";
        }
        if(isDraw()) {
            keepPlaying = false;
            return "Game is a draw!";
        }
        return "";
    }


    /**
     * This isDraw method will determine if the game is a draw due to
     * all choices made and no one player had a winning combination
     *
     * @return true if all values have been picked, game is a DRAW, false otherwise
     */
    public static boolean isDraw() {

        return player1Square.getChoices() + player2Square.getChoices() == draw;
    }


    /**
     * This isWin method determines if a player's magic square choices
     * result in a winning combination
     * @param playerSquare the players magic square
     * @return true, if the player has won, false otherwise
     */
    public static boolean isWin(MagicSquares playerSquare) {
        //winning conditions array
        int[] victoryConditions = new int[] {98, 273, 140, 266, 84, 161, 56, 146};
        for (int i = 0; i < victoryConditions.length; i++) {
            if(victoryConditions[i] == (playerSquare.getChoices() & victoryConditions[i])) {
                return true;
            }
            if(isDraw()) {
                return false;
            }
        }
        return false;
    }


    /**
     * This getSelection method asks the player for their number selection from 1 - 9.
     * If the user does not enter a number in this
     * range, they are continually prompted until they do enter a number between 1 and 9.
     * If the user has already entered a number that has been chosen, they are continually
     * prompted until they enter a number that has not been previously selected.
     * @param player - Player
     * @param player1Square the square of player choices
     * @param player2Square   the square of player choices
     * @return the number chosen from 1-9
     *
     */
    public static int getSelection(String player, MagicSquares player1Square, MagicSquares player2Square) {
        byte selection = 0;
        boolean win = true;
        while(win) {
            System.out.println(player + ", please enter a number: ");
            selection = (byte) input.nextInt();
            try {
                if (selection < 1 || selection > 9) {
                    System.out.println("Number is not between 1 and 9");
                    continue;
                }
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Number is not between 1 and 9");
                continue;
            }


            if(player1Square.hasAlreadyChosen(selection) || (player2Square.hasAlreadyChosen(selection))) {
                System.out.println("Number has already been chosen");
            }
            else {
                win = false;
            }
        }


        return selection;
    }


    /**
     * This printDirections method prints out the game introduction
     */
    public static void printDirections() {

        System.out.println("Welcome to the game of Magic Squares");
        System.out.println("************************************");
        System.out.println("Rules: " + "\n" + "2 players play the game.");
        System.out.println("Each player takes turns picking a number" +
                " 1 - 9." + "\n" + "No number can be chosen twice" + "\n"
                + "The first player to have 3 numbers that sum to 15 wins!" + "\n" +
                "2 7 6 " + "\n" + "9 5 1" + "\n" + "4 3 8");
        System.out.println("************************************");


    }


    @Override
    public String toString() {
        return "MagicSquareGame{" +
                "player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                '}';
    }
}

