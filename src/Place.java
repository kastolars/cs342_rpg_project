import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Place {
    private int ID;
    private String name;
    private String description;
    private ArrayList<Direction> directions = new ArrayList<Direction>();
    private ArrayList<Character> characters = new ArrayList<Character>();
    private static HashMap<String, Artifact> artifacts = new HashMap<String, Artifact>();
    public static HashMap<Integer, Place> places = new HashMap<Integer, Place>();


    Place(Scanner sc, int version){}

    Place(int ID, String name, String description){
        this.ID = ID;
        this.name = name;
        this.description = description;
    }

    public static Place getPlaceByID(int ID){
        return places.get(ID);
    }

    public void addDirection(Direction d){
        directions.add(d);
    }

    public void addArtifact(Artifact a){
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
        System.out.println(String.format("ID: ", ID));
        System.out.println(String.format("Name: ", name));
        System.out.println(String.format("Description: ", description));
    }

    public void display(){
        System.out.println(name);
        System.out.println(description);
    }
}
