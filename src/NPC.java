/*
Author: Karol Stolarski
netID: kstola2

Non-player character extension of Character class.
Defines its own version of handling go to prevent it
from going to the exit.
 */

public class NPC extends Character {


    public NPC(int ID, String name, String desc, int placeID) {
        super(ID, name, desc, placeID);
        decisionMaker = new AI();
    }

    @Override
    protected boolean handleGo(String d) {
        Place temp = currentPlace;
        currentPlace.removeCharacter(this);
        currentPlace = currentPlace.followDirection(d, this);
        if (currentPlace.isExit()){
            currentPlace = temp;
        }
        currentPlace.addCharacter(this);
        return false;
    }
}
