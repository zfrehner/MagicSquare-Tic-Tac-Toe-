package magicsquares;

/**
 * @author zach frehner
 * @version 1
 */
public class MagicSquares {

    //Holds a 16 bit integer that contains all numbers chosen for a player.
    // Each of the first nine bits, from right-to-left, represent the numbers 1-9
    private short choices = 0;
    //magic square array holding the numbers in order
     public static int[] magicSquare = new int[] {2, 7, 6, 9, 5, 1, 4, 3, 8};




    /**
     * Instantiates a magic square object
     */
    public MagicSquares() {
        this.choices = (short) 0;
    }

    /**
     * Accepts a byte value ranging between 1-9.
     * This will set the bit at the index selection - 1 to "on."
     * This method then returns true if the bit was changed, false if
     * the selection has already been chosen, and throws an IllegalArgumentException
     * if selection is less than 1 or greater than 9
     * @param selection the players choice 1-9
     * @return true if bit was changed false if selection is already chosen
     * @throws IllegalArgumentException for selections < 1 and > 9
     * */
    public boolean choose(byte selection) throws IllegalArgumentException {
        //check for out of bounds
        if(selection < 1 || selection > 9) {
            throw new IllegalArgumentException("The selection you provided is out of bounds. Please enter" +
                    " a selection between 1 and 9.");
        }
        //check if the selection has already been chosen
        if(hasAlreadyChosen(selection)) {
            System.out.println("The number you provided has already been chosen, please choose again.");
            return false;
        }

        //create the mask
        short mask = createMask(selection);
        //choices gets assigned the selection by using the "or" (|) operator
        choices = (short) (choices | mask);
        return true;
        //return (choices & mask) == mask;
    }

    //helper method for the methods that need a bit mask
    private short createMask(byte selection) {
        //create a mask where the bit of interest (index) is 1
        short mask = 1; //assign decimal 1 / 0b0000_0001
        //shift the mask over index shifts
        mask = (short) (mask << selection -1);

        return mask;
    }

    /**
     * Returns true if the bit at index selection - 1
     * is set to the "on" position, or false if the bit is in the "off" position
     * @param selection the selection of the player 1-9
     * @return true if bit @selection -1 is true, false if in off position
     */
    public boolean hasAlreadyChosen(byte selection) {
        //create mask for "and-ing"
        short mask = createMask(selection);
        return (mask & choices) == mask;
    }

    /**
     *  Getter for the choices field. This number can then
     *  be examined to determine if the player has won the match or not.
     * @return the choices (selections) of the player
     */
    public short getChoices() {

        return choices;
    }

    /**
     *Prints a string representation of the magic square.
     *  Each number is printed in the position located in the first
     *  image at the top of this document. Any number not chosen will
     *  have an underscore in that position
     */
    public void printChoices() {
        //look at choices data and figure out which bits are set
        //see if the selection is chosen - if it is, print the number
        //if not, print an "_"
        //public int[] magicSquare = new int[] {2, 7, 6, 9, 5, 1, 4, 3, 8};
        int count = 1;
        for (int i = 0; i < magicSquare.length; i++) {
                if (hasAlreadyChosen((byte) magicSquare[i])) {
                    System.out.print(magicSquare[i] + " ");

                } else {
                    System.out.print("_ ");
                }
            if(count == 3 || count == 6 || count == 9) {
                System.out.println();
            }
            count++;
            }

    }

    //testing main
   /* public static void main(String[] args) {
        MagicSquares square = new MagicSquares();

        //System.out.println(String.format("%09d", Integer.parseInt(Integer.toBinaryString(square.getChoices()))));
        //byte x = 5;
        byte y = 10;
        //byte z = 5;
        byte a = 2;
        byte b = 6;
        byte c = 7;
        //byte d = 9;
        //square.choose(x);
        System.out.println(String.format("%09d", Integer.parseInt(Integer.toBinaryString(square.getChoices()))));
        //square.printChoices();

        *//*square.choose(z);
        System.out.println(String.format("%09d", Integer.parseInt(Integer.toBinaryString(square.getChoices()))));*//*


        square.choose(a);
        square.choose(b);
        square.choose(c);
        //square.choose(d);
        System.out.println(String.format("%09d", Integer.parseInt(Integer.toBinaryString(square.getChoices()))));
        square.printChoices();
        System.out.println(square.getChoices());


        *//*square.choose(y);
        System.out.println(String.format("%09d", Integer.parseInt(Integer.toBinaryString(square.getChoices()))));*//*
  }

*/


}

