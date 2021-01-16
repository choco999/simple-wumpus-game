/**
 * Application Purpose: a class for giving a hint
 * Author: Chisato Sakata
 * Date: December 7th 2020
 * Time: 7:08AM
 */

public class Hint {

    // a method to give hints (overloading)
    public static void giveHint(int column){
        if(column < 4){
            System.out.println("The wumpus is in column 0,1,2, or 3");
        } else {
            System.out.println("The wumpus is in column 4,5, or 6");
        }
    }

    public static void giveHint(int row, int column){
        System.out.println("The row number and the column number is the same!");
    }
}
