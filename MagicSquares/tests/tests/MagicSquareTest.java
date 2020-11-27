package tests;

import static org.junit.Assert.*;

import magicsquares.MagicSquares;
import org.junit.*;
import java.io.ByteArrayOutputStream; // needed to redirect Standard Out
import java.io.PrintStream; // needed to redirect Standard Out

/**
 * Magic square test for the magic square class
 * @author Most of this is from Susan Uland
 * @version 1.0
 */
public class MagicSquareTest {

    private static final byte[] selections = {2, 7, 6, 9, 5, 1, 4, 3, 8};
    private static final short[] cummulativeChoices = {0b0_0000_0010,
            0b0_0100_0010,
            0b0_0110_0010,
            0b1_0110_0010,
            0b1_0111_0010,
            0b1_0111_0011,
            0b1_0111_1011,
            0b1_0111_1111,
            0b1_1111_1111};


    /**
     * method for testing the constructor of the magic square class
     */
    @Test
    public void testConstructor() {
        //TODO
        MagicSquares square = new MagicSquares();
        assertEquals("All choices should be 0", (short) 0, square.getChoices());
    }

    /**
     * testing the printChoices method on an empty square
     */
    @Test
    public void testPrintChoicesEmptySquare() {
        MagicSquares ms = new MagicSquares();


        // redirect output from console window into a PrintStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // invoke printChoices which now prints to the PrintStream instead of the console window
        ms.printChoices();

        String expectedConsoleOutput = "_ _ _ "+ System.lineSeparator()+"_ _ _ "+System.lineSeparator()+"_ _ _ "+System.lineSeparator();
        assertEquals("print choices incorrect output", expectedConsoleOutput, out.toString());

    }

    /**
     * testing the choose method
     */
    @Test
    public void testChoose() {
        MagicSquares sq = new MagicSquares();

        // for every selection choice in values array
        for (int i = 0; i < selections.length; i++) {


            // choose num
            sq.choose((byte) selections[i]);

            // verify that getChoices returns proper cummulative selections
            assertEquals("choice was noted incorrectly", cummulativeChoices[i], sq.getChoices());

        }
    }

    /**
     * Method for testing the hasAlreadyChosen method
     * constructs a magic square, chooses one value
     * chooses another value that is the same as the first which will cause the error
     */
    @Test
    public void testHasAlreadyChosen() {
        // TODO
        MagicSquares square = new MagicSquares();
        byte selection = 5;
        byte selection2 = 5;
        assertEquals("The selection has already been chosen", true, square.choose(selection));
        assertEquals("The selection has not already been chosen", true, square.hasAlreadyChosen(selection2));


    }

    /**
     * testing to make sure the selection is within the boundary 1-9
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidChoice() {
        MagicSquares ms = new MagicSquares();
        ms.choose((byte) -7);
        ms.choose((byte) 10);
    }

    /**
     * testing the printChoices method for a full square
     */
    @Test
    public void testPrintChoicesFullSquare() {
        MagicSquares ms = new MagicSquares();

        // choose every selection in the values array
        for (byte selection : selections) {
            ms.choose((byte) selection);
        }

        // redirect output from console window into a PrintStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // invoke printChoices which now prints to the PrintStream instead of the console window
        ms.printChoices();

        String expectedConsoleOutput = "2 7 6 "+System.lineSeparator()+"9 5 1 "+System.lineSeparator()+"4 3 8 "+System.lineSeparator();
        assertEquals("print choices incorrect output", expectedConsoleOutput, out.toString());

    }

}