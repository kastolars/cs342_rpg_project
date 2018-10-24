import java.util.Scanner;

public class Player extends Character {
    private UI decisionmaker = new UI();

    public Player(int ID, String name, String desc, int placeID) {
        super(ID, name, desc, placeID);
    }
}
