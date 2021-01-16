/** Application Purpose: Create a blueprint class for wumpus object
 * Author: Chisato Sakata
 * Date: December 5th 2020
 * Time: 1:16AM
 */

public class Wumpus {
    // instance variables
    private int wumpusX;
    private int wumpusY;

    // a constructor that takes 2 arguments
    public Wumpus(int wumpusX, int wumpusY){
        this.wumpusX = wumpusX;
        this.wumpusY = wumpusY;
    }

    // a setter and getter for wumpusX
    public void setWumpusX(int wumpusX) {
        this.wumpusX = wumpusX;
    }

    public int getWumpusX() {
        return wumpusX;
    }

    // a setter and getter for wumpusY
    public void setWumpusY(int wumpusY) {
        this.wumpusY = wumpusY;
    }

    public int getWumpusY() {
        return wumpusY;
    }

    // a method to check if the user found the wumpus
    public boolean isWumpus(int userX, int userY){
        if(userX == wumpusX && userY == wumpusY){
            return true;
        } else {
            return false;
        }
    }

    // a method to calculate the distance from the wumpus
    public int calculateDistance(int userX, int userY){
        return Math.abs(wumpusX - userX) + Math.abs(wumpusY - userY);
    }
}
