import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Place {
    private int ID;
    private String name;
    private String description;

    public static HashMap<Integer, Place> places = new HashMap<Integer, Place>();
    private ArrayList<Direction> directions = new ArrayList<Direction>();
    private ArrayList<Character> characters = new ArrayList<Character>();
    private HashMap<String, Artifact> artifacts = new HashMap<String, Artifact>();

    Place(Scanner sc, int version){
        String line;
        int count, i;
        description = "";

        // Get ID and name
        line = CleanLineScanner.getCleanLine(sc);
        ID = Integer.valueOf(line.replaceAll("\\D.*", ""));
        name = line.substring(line.indexOf(String.valueOf(ID)) + String.valueOf(ID).length()).trim();

        // Get number of description lines
        line = CleanLineScanner.getCleanLine(sc);
        count = Integer.valueOf(line.replaceAll("\\D.*", ""));

        // Complete description
        for (i = 0; i < count; i++){
            description += CleanLineScanner.getCleanLine(sc) + "\n";
        }

        // Add the place to the collection of places.
        places.put(ID, this);
    }

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

    public static void printAll(){}

    public void print(){
        System.out.println(String.format("ID: %d", ID));
        System.out.println(String.format("Name: %s", name));
        System.out.println(String.format("Description: %s", description));
    }

    public void display(){
        System.out.println(name);
        System.out.println(description);
    }
}
