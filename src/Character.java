import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.Math.random;

public class Character {
    protected int version;
    protected int ID;
    protected String name;
    protected String description;
    public static HashMap<Integer, Character> characters = new HashMap<Integer, Character>();
    protected HashMap<String, Artifact> artifacts = new HashMap<String, Artifact>();
    protected Place currentPlace;
    protected DecisionMaker decisionMaker;

    public Character(Scanner sc, int version){
        String line;
        int placeID, count, i;
        description = "";

        // Character type
        line = CleanLineScanner.getCleanLine(sc);
        String charType = line.replaceAll("\\d", "").trim();
        if (line.replaceAll("\\d", "").trim().matches("PLAYER")) {
            decisionMaker = new UI();
        } else {
            decisionMaker = new AI();
        }
//        ct = CharType.valueOf(line.replaceAll("\\d", "").trim());
//        if (ct.toString() == )

        // Starting location
        placeID = CleanLineScanner.extractInt(line);

        // ID and name
        line = CleanLineScanner.getCleanLine(sc);
        ID = CleanLineScanner.extractInt(line);
        name = line.substring(line.indexOf(String.valueOf(ID)) + String.valueOf(ID).length()).trim();

        // Get number of description lines
        line = CleanLineScanner.getCleanLine(sc);
        count = CleanLineScanner.extractInt(line);

        // Complete description
        for (i = 0; i < count; i++){
            description += CleanLineScanner.getCleanLine(sc) + "\n";
        }


        // Random location check
        if (placeID == 0){
            int numPlaces = Place.places.size();
            placeID = (int) Math.random() * (numPlaces - 2);
        }

        // Set current place
        currentPlace = Place.getPlaceByID(placeID);

        // Add character to collections
        Place.getPlaceByID(placeID).addCharacter(this);
        characters.put(ID, this);
    }

    public Character(int ID, String name, String desc){
        this.ID = ID;
        this.name = name;
        this.description = desc;
    }

    public static Character getCharacterByID(int ID){
        return characters.get(ID);
    }

    public void makeMove(){}

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
        artifacts.put(a.name(), a);
    }

    public void removeArtifact(Artifact a) { artifacts.remove(a.name());}
}
