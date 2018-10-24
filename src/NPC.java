import java.util.Scanner;

public class NPC extends Character {


    public NPC(int ID, String name, String desc, int placeID) {
        super(ID, name, desc, placeID);
        decisionMaker = new AI();
    }
}
