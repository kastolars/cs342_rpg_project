import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.Math.random;

public class Character {
    private int version;
    private int ID;
    private String name;
    private String description;
    private CharType ct;
    public static HashMap<Integer, Character> characters = new HashMap<Integer, Character>();
    private HashMap<String, Artifact> artifacts = new HashMap<String, Artifact>();

    public Character(Scanner sc, int version){
        String line;
        int placeID, count, i;
        description = "";

        // Character type
        line = CleanLineScanner.getCleanLine(sc);
        ct = CharType.valueOf(line.replaceAll("\\d", "").trim());

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

        // Add character to collections
        Place.getPlaceByID(placeID).addCharacter(this);
        characters.put(ID, this);
    }

    public Character(int ID, String name, String desc){
        this.ID = ID;
        this.name = name;
        this.description = desc;
    }

    private enum CharType{
        PLAYER("Player"),
        NPC("NPC");

        private final String type;

        CharType(String type){
            this.type = type;
        }

        public String toString(){
            return type;
        }

        private boolean match(String s) {
            return s.matches("(?i)" + type);
        }

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
}
