import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Place {
    private int ID;
    private String name;
    private String description;

    public static HashMap<Integer, Place> places = new HashMap<Integer, Place>(); // Collection for places
    private ArrayList<Direction> directions = new ArrayList<Direction>(); // Contains all this place's directions
    private ArrayList<Character> characters = new ArrayList<Character>(); // Contains all this place's characters
    private HashMap<String, Artifact> artifacts = new HashMap<String, Artifact>(); // Contains all this place's artifacts

    // Constructor for Place class
    Place(Scanner sc, int version){
        description = ""; // initialize description to empty string

        // Get ID and name
        String line = CleanLineScanner.getCleanLine(sc);
        ID = Integer.valueOf(line.replaceAll("\\D.*", ""));
        name = line.substring(line.indexOf(String.valueOf(ID)) + String.valueOf(ID).length()).trim();

        // Get number of description lines
        line = CleanLineScanner.getCleanLine(sc);
        int count = Integer.valueOf(line.replaceAll("\\D.*", ""));

        // Complete description
        for (int i = 0; i < count; i++){
            description += CleanLineScanner.getCleanLine(sc) + "\n";
        }

        // Add the place to the collection of places.
        places.put(ID, this);
    }

    // Used to construct nowhere and exit
    Place(int ID, String name, String description){
        this.ID = ID;
        this.name = name;
        this.description = description;
        places.put(ID, this);
    }

    public static Place getPlaceByID(int ID){
        return places.get(ID);
    }

    public void addDirection(Direction d){
        directions.add(d);
    }

    public void addArtifact(Artifact a){
        artifacts.put(a.name(), a);
    }

    public Artifact removeArtifactByName(String name){
        Artifact a = artifacts.get(name);
        artifacts.remove(name);
        return a;
    }

    public String getRandomArtifact(){
        return (String) artifacts.keySet().toArray()[new Random().nextInt(artifacts.size())];
    }

    public void addCharacter(Character c){
        characters.add(c);
    }

    public void removeCharacter(Character c){
        characters.remove(c);
    }

    public void useKey(Artifact a){}

    public Place followDirection(String s){
        for (Direction d : directions) {
            if (d.match(s)) {
                try {
                    return d.follow();
                } catch (Direction.LockedDirectionException e){
                    return this;
                }
            }
        }
        return this;
    }

    public static void printAll(){
        for (Place p : places.values()){

        }
    }

    public void print(){
        System.out.println(String.format("ID: %d", ID));
        System.out.println(String.format("Name: %s", name));
        System.out.println(String.format("Description: %s", description));
        System.out.println("Directions: ");
        for (Direction d : directions){
            d.print();
        }
        System.out.println("Artifacts: ");
        for (Artifact a : artifacts.values()){
            a.print();
        }
        System.out.println("Characters: ");
        for (Character c : characters) {
            c.print();
        }
    }

    public void display(){
        System.out.println(name);
        System.out.println(description);
    }

    public boolean isExit(){
        return ID == 1;
    }

    public String getRandomDirection() {
        Direction d = (Direction) directions.toArray()[new Random().nextInt(directions.size())];
        return d.getDir();
    }
}
