/*
Author: Karol Stolarski
netID: kstola2

The character class introduces moving entities in the game space.
Characters can be Player-controlled or not.
Characters are built by the Character factory.
All the move logic is held in a switch case that handles
all commands.
Commands are passed via an implementation of the
decisionmaker interface.
 */

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

    // Arguments constructor
    public Character(int ID, String name, String description, int placeID){
        this.ID = ID;
        this.name = name;
        this.description = description;

        // starting location
        if (placeID == 0){
            do {
                currentPlace = (Place) Place.places.values().toArray()[new Random().nextInt(Place.places.size())];
            } while (currentPlace.isExit()); // keeps the constructor from initializing to exit
        } else {
            currentPlace = Place.getPlaceByID(placeID);
        }
        currentPlace.addCharacter(this);

        characters.put(ID, this);
    }

    // Returns character by ID
    public static Character getCharacterByID(int ID){
        return characters.get(ID);
    }

    // Move logic
    public boolean makeMove() {
        Move m;
        try { // Get the type of move from the decision maker
            m = decisionMaker.getMove(this, currentPlace);
        } catch (IllegalArgumentException e) {
            System.out.println("Not a valid command!\n");
            return false;
        }
        Move.MoveType mt = m.getType();
        String arg = m.getArgument();
        switch (mt) {  // switch case governs which move branch to execute
            case USE:           // Returns false if the current place is an exit.
                handleUse(arg); // Otherwise it returns true and the Game class
                return false;   // will terminate the loop.
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

                return true;
            case LOOK:
                currentPlace.display();
                this.makeMove();
            default:
                return false;
        }
    }

    protected abstract boolean handleGo(String d); // Child class defines this

    private void handleUse(String s){ // Try to handle a use move
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

    public void print(){ // print relevant debug information
        System.out.println(String.format("ID: %d", ID));
        System.out.println(String.format("Name: %s", name));
        System.out.println(String.format("Description: %s", description));
    }

    public void display(){ // displays information for the player
        System.out.println(name);
        System.out.println(description);
    }

    // Adds an artifact to the character's inventory
    public void addArtifact(Artifact a){
        artifacts.add(a);
    }

    // Removes an artifact from the character's inventory
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
        // Only prints out errors if this is a player
        if (this instanceof Player){
            System.out.println(String.format("You don't possess an item called %s.", s));
        }
        return null;
    }

    // Gets a random artifact from the inventory
    public Artifact getRandomArtifact() {
        try {
            return (Artifact) artifacts.toArray()[new Random().nextInt(artifacts.size())];
        } catch (IllegalArgumentException e){
            return null;
        }
    }

    // Displays all the items in this character's possession
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

    // Returns the character's name
    public String name() {
        return name;
    }
}
