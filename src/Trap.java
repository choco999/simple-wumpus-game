/**
 * Application Purpose: a class for trapping the user
 * Author: Chisato Sakata
 * Date: December 7th 2020
 * Time: 6:30AM
 */

public class Trap {
    // instance variables
    private int trapX;
    private int trapY;

    // a constructor that takes 2 arguments
    public Trap(int trapX, int trapY){
        this.trapX = trapX;
        this.trapY = trapY;
    }

    // a setter and getter for trapX
    public void setTrapX(int trapX) {
        this.trapX = trapX;
    }

    public int getTrapX() {
        return trapX;
    }

    // a setter and getter for trapY
    public void setTrapY(int trapY) {
        this.trapY = trapY;
    }

    public int getTrapY() {
        return trapY;
    }

    // a method to check the user is trapped
    public boolean isTrap(int userX, int userY){
        if(userX == trapX && userY == trapY){
            return true;
        } else {
            return false;
        }
    }
}
