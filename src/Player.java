/*
Author: Karol Stolarski
netID: kstola2

Player extension of the Character class
Defines its own version of handle Go to check
whether the player has exited the game space.
 */

public class Player extends Character {

    public Player(int ID, String name, String desc, int placeID) {
        super(ID, name, desc, placeID);
        decisionMaker = new UI();
    }

    @Override
    protected boolean handleGo(String d) {
        currentPlace.removeCharacter(this);
        currentPlace = currentPlace.followDirection(d, this);
        currentPlace.addCharacter(this);
        if (currentPlace.isExit()){
            System.out.println("You've reached the exit.");
        }
        return currentPlace.isExit();
    }
}
