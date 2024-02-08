package com.wizard;


//this class allows you to make calls and retrive info on the specific candy in use, it will also be useful in
//implementing runes later on!

public class Candy {

    public static int curCandy = 3;

    public static void setCandy(int i) {
        curCandy = i;
    }

    /* candy index's
    defult mint
    0 mint
    1 tic tac
    2 lolipop
    3 candyblast
     */

    //This allows the call in World to get the correct time since last used for the specific wepon, this system should ensure there is
    //no double pump type statagys in our game and runes could be implemented easly as a multiplyer on the cooldown.

    public static int getCandyCooldown() {

        if (curCandy == 0) {
            return 500;
        }

        else if (curCandy == 1) {
            return 300;
        }

        else if (curCandy == 2) {
            return 900;
        }

        else if (curCandy == 3) {
            return 1200;
        }

        else{ return 500;}
    }


    //this will call the projectile maneger to whatever candy is currently being used

    public static void useCandy(float dx, float dy, float x, float y) {
        if (curCandy == 0) {
            ProjectileManager.createMint( dx,  dy,  x,  y);
        }

        else if (curCandy == 1) {
            ProjectileManager.createTicTac( dx,  dy,  x,  y);
        }

        else if (curCandy == 2) {
            ProjectileManager.createLollipop( dx,  dy,  x,  y);
        }

        else if (curCandy == 3) {
            ProjectileManager.createCandyBlast( dx,  dy,  x,  y);
        }

        else {ProjectileManager.createMint( dx,  dy,  x,  y);}
    }

}
