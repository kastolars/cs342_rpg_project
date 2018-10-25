import java.util.*;

public abstract class Character {
    protected int version;
    protected int ID;
    protected String name;
    protected String description;
    protected ArrayList<Artifact> artifacts = new ArrayList<Artifact>();
    protected Place currentPlace;
    public static HashMap<Integer, Character> characters = new HashMap<Integer, Character>();
    protected DecisionMaker decisionMaker;

    public Character(int ID, String name, String description, int placeID){
        this.ID = ID;
        this.name = name;
        this.description = description;

        // starting location
        if (placeID == 0){
            do {
                currentPlace = (Place) Place.places.values().toArray()[new Random().nextInt(Place.places.size())];
            } while (currentPlace.isExit());
        } else {
            currentPlace = Place.getPlaceByID(placeID);
        }
        currentPlace.addCharacter(this);

        characters.put(ID, this);
    }

    public static Character getCharacterByID(int ID){
        return characters.get(ID);
    }

    public boolean makeMove() {
        Move m;
        try {
            m = decisionMaker.getMove(this, currentPlace);
        } catch (IllegalArgumentException e) {
            System.out.println("Not a valid command!\n");
            return false;
        }
        Move.MoveType mt = m.getType();
        String arg = m.getArgument();
        switch (mt) {
            case USE:
                handleUse(arg);
                return false;
            case GET:
                Artifact a = currentPlace.removeArtifactByName(arg);
                addArtifact(a);
                return false;
            case DROP:
                a = removeArtifact(arg);
                return false;
            case INVENTORY:
                displayInventory();
                return false;
            case GO:
                return handleGo(arg);
            case EXIT:
            case QUIT:
                System.out.println("Exiting game...");
                return true;
            case LOOK:
                currentPlace.display();
            default:
                return false;
        }
    }

    protected abstract boolean handleGo(String d);

    private void handleUse(String s){
        try {
            for (Artifact a : artifacts){
                if (a.match(s)){
                    currentPlace.useKey(a, this);
                }
            }
        } catch (NullPointerException e) {
            return;
        }
    }

    public void print(){
        System.out.println(String.format("ID: %d", ID));
        System.out.println(String.format("Name: %s", name));
        System.out.println(String.format("Description: %s", description));
    }

    public void display(){
        System.out.println(name);
        System.out.println(description);
    }

    public void addArtifact(Artifact a){
        artifacts.add(a);
    }

    public Artifact removeArtifact(String s) {
        try {
            for (Artifact a : artifacts) {
                if (a.match(s)){
                    artifacts.remove(a);
                    currentPlace.addArtifact(a);
                    return a;
                }
            }
        } catch (NullPointerException e){
            return null;
        }
        if (this instanceof Player){
            System.out.println(String.format("You don't possess an item called %s.", s));
        }
        return null;
    }

    public Artifact getRandomArtifact() {
        try {
            return (Artifact) artifacts.toArray()[new Random().nextInt(artifacts.size())];
        } catch (IllegalArgumentException e){
            return null;
        }
    }

    private void displayInventory(){
        if (artifacts.size() < 1){
            System.out.println("Your inventory is empty.");
            return;
        } else {
            System.out.println("Current inventory: \n");
            for (Artifact a : artifacts){
                a.display();
            }
        }
    }

    public String name() {
        return name;
    }
}
