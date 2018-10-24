

public class Player extends Character {

    public Player(int ID, String name, String desc, int placeID) {
        super(ID, name, desc, placeID);
        decisionMaker = new UI();
    }
}
