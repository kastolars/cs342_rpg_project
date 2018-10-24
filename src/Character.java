import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.Math.random;

public class Character {
    protected int version;
    protected int ID;
    protected String name;
    protected String description;
    protected HashMap<String, Artifact> artifacts = new HashMap<String, Artifact>();
    protected Place currentPlace;
    public static HashMap<Integer, Character> characters = new HashMap<Integer, Character>();

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
