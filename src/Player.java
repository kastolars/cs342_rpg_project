

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
        return currentPlace.isExit();
    }
}
