/** Application Purpose: test wumpus game
 * Author: Chisato Sakata
 * Date: December 5th 2020
 * Time: 1:30AM
 */

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class WumpusTestHarness {
    public static void main(String[] args){

        boolean isContinued = true;
        while(isContinued){ // the game continues until the user select not to continue
            System.out.println("Wumpus is hidden! Find the Wumpus!");

            // generate random numbers
            Random randomNumber = new Random();
            int wumpusX = randomNumber.nextInt(7);
            int wumpusY = randomNumber.nextInt(7);
            int trapX = randomNumber.nextInt(7);
            int trapY = randomNumber.nextInt(7);

            // if the wumpous position and the trap position is the same, change the trap position
            while (wumpusX == trapX && wumpusY == trapY){
                trapX = randomNumber.nextInt(7);
                trapY = randomNumber.nextInt(7);
            }

            // instanciate wumpus1 and trap1 objects
            Wumpus wumpus1 = new Wumpus(wumpusX, wumpusY);
            Trap trap1 = new Trap(trapX, trapY);

            // for debugging
            //System.out.println("wumpus: " + wumpus1.getWumpusX() + " " + wumpus1.getWumpusY());
            //System.out.println("trap: " + trap1.getTrapX() + " " + trap1.getTrapY());

            int count = 0; // a count for changing the wumpus position
            int hintCount = 0; // a count for giving a hint
            int total = 0; // how many tries did the user take to find the wumpus

            // user prompt
            boolean isValid = true;
            do {
                Scanner in = new Scanner(System.in);
                try { // the user will enter 0-6 numbers for row and column
                    System.out.println("---------------------------------");
                    System.out.println("Enter a row number from 0 to 6");
                    int guessX = in.nextInt(7);
                    System.out.println("Enter a column number from 0 to 6");
                    int guessY = in.nextInt(7);

                    // check if the user found the wumpus
                    if(wumpus1.isWumpus(guessX, guessY)){
                        System.out.println("You found the wumpus!");
                        isValid = false;
                    } else if(trap1.isTrap(guessX, guessY)){ // if the user couldn't find the wumpus in 5 tries, the wumpus' position will change
                        System.out.println("You are trapped! The wumpus is hidden somewhere else!");
                        wumpusX = randomNumber.nextInt(7);
                        wumpusY = randomNumber.nextInt(7);
                        wumpus1.setWumpusX(wumpusX);
                        wumpus1.setWumpusY(wumpusY);
                    } else { // if the user couldn't find the wumpus, tell them how they are far from the wumpus
                        int position = wumpus1.calculateDistance(guessX, guessY);
                        switch(position) {
                            case 1:
                            case 2:
                            case 3:
                                System.out.println("You are very close!");
                                break;
                            case 4:
                            case 5:
                            case 6:
                                System.out.println("You are a little close");
                                break;
                            case 7:
                            case 8:
                            case 9:
                                System.out.println("You are far");
                                break;
                            case 10:
                            case 11:
                            case 12:
                                System.out.println("You are very far");
                                break;
                        }
                        System.out.println("You are " + wumpus1.calculateDistance(guessX, guessY) + " away from the wumpus");
                        count++;
                        hintCount++;
                        if(count == 6){ // if the user couldn't find the wumpus in 5 times, the wumpus is hidden in the other position
                            System.out.println("The wumpus is hidden somewhere else!");
                            wumpusX = randomNumber.nextInt(7);
                            wumpusY = randomNumber.nextInt(7);
                            wumpus1.setWumpusX(wumpusX);
                            wumpus1.setWumpusY(wumpusY);
                            count = 0; // reset the count
                        }
                        if(hintCount == 3){ // if the user couldn't find the wumpus in 2 times, ask the user for a hint
                            System.out.println("---------------------------");
                            System.out.println("Do you need a hint?");
                            System.out.println("Enter y to get a hint");

                            // user prompt(char)
                            char answer = in.next().charAt(0);
                            Character answerChar = answer;

                            // get a wumpus position
                            int row = wumpus1.getWumpusX();
                            int column = wumpus1.getWumpusY();

                            // if the user type 'y', give a hint depends on the wumpus position
                            if(answerChar.equals('y')){
                                System.out.println("Hint: ");
                                if(column == row){ // if wumpusX and wumpusY is the same number
                                    Hint.giveHint(row, column);
                                } else { // give a hint as to which half the wumpus is in
                                    Hint.giveHint(column);
                                }
                                hintCount = 0;
                            } else { // this is when the user doesn't type 'y'
                                System.out.println("You don't need a hint? Good luck!");
                                hintCount = 0;
                            }
                        }
                    }
                    total++;
                } catch(InputMismatchException e) { // if the user entered an invalid number, it throws an exception and ask them again
                    System.out.println("The number is invalid. Try again.");
                    in.nextLine();
                }
            } while(isValid);


            // a comment depends on how many times the user tried
            System.out.println("-------------------------");
            System.out.println("You tried " + total + " times");
            String[] comments = {"You were really lucky", "Great!", "You can do better next time!"};
            if(total == 1){
                System.out.println(comments[0]);
            } else if(total < 7){
                System.out.println(comments[1]);
            } else {
                System.out.println(comments[2]);
            }

            // show the result visually
            System.out.println("Here is the result");
            System.out.println("The wumpus is 1 and the trap is #");
            int[][] grid = new int[7][7];
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if(j == wumpus1.getWumpusX() && i == wumpus1.getWumpusY()){
                        System.out.print("1  ");
                    }
                    else if (j == trap1.getTrapX() && i == trap1.getTrapY()){
                        System.out.print("#  ");
                    } else {
                        System.out.print(grid[i][j] + "  ");
                    }
                }
                System.out.println();
            }

            // after the user find the wumpus, they choose to continue the game
            System.out.println("Do you want to try again?(yes to continue)");
            Scanner in = new Scanner(System.in);
            String userAnswer = in.nextLine();
            if(userAnswer.equals("yes")){
                isContinued = true;
            } else {
                System.out.println("This is the end of the game");
                isContinued = false;
            }
        }
    }
}
