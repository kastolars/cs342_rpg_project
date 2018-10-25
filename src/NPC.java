import java.util.Scanner;

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
