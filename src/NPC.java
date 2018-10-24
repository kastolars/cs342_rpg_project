import java.util.Scanner;

public class NPC extends Character {
    private AI decisionmaker = new AI();

    public NPC(int ID, String name, String desc, int placeID) {
        super(ID, name, desc, placeID);
    }
}
