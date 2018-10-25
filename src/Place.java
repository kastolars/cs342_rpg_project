/*
Author: Karol Stolarski
netID: kstola2

The place class acts as storage for all the different
components of the game. At any time, a place can have
filled a collection of characters, artifacts, and directions.
The interoperability between these different parts of the game
all depend on location which is represented by the Place class.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Place {
    private int ID;
    private String name;
    private String description;

    public static HashMap<Integer, Place> places = new HashMap<Integer, Place>(); // Collection for places
    public static int firstPlace;
    private ArrayList<Direction> directions = new ArrayList<Direction>(); // Contains all this place's directions
    private ArrayList<Character> characters = new ArrayList<Character>(); // Contains all this place's characters
    private ArrayList<Artifact> artifacts = new ArrayList<Artifact>(); // Contains all this place's artifacts


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
        if (places.isEmpty()){
            firstPlace = ID;
        }
        places.put(ID, this);
    }

    // Used to construct nowhere and exit
    Place(int ID, String name, String description){
        this.ID = ID;
        this.name = name;
        this.description = description;
        places.put(ID, this);
    }

    // Retrieve a place by its ID number from the static collection of places
    public static Place getPlaceByID(int ID){
        return places.get(ID);
    }

    // Add a direction to this Place
    public void addDirection(Direction d){
        directions.add(d);
    }

    // Add an artifact to this Place
    public void addArtifact(Artifact a){
        try {
            artifacts.add(a);
        } catch (NullPointerException e){
            return;
        }
    }

    // Remove an artifact given its name
    public Artifact removeArtifactByName(String name){
        for (Artifact a : artifacts){
            if (a.match(name)){
                if (a.mobility() > -1) {
                    artifacts.remove(a);
                    return a;
                } else { // Will not pick up chests or other super-heavy artifacts
                    System.out.println("It won't budge!");
                }
            }
        }
        return null;
    }

    // Returns a random artifact in the place
    public Artifact getRandomArtifact(){
        try {
            Artifact a;
            do {
                a = (Artifact) artifacts.toArray()[new Random().nextInt(artifacts.size())];
            } while (a.mobility() < 0);
            return a;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    // Adda character to this place
    public void addCharacter(Character c){
        characters.add(c);
    }

    // Remove a character from this place
    public void removeCharacter(Character c){
        characters.remove(c);
    }

    // Attempt to use a key on all the directions in this place
    public void useKey(Artifact a, Character c){
        for (Direction d : directions){
            d.useKey(a, c);
        }
    }

    // Attempt to follow a direction. If it's locked, return the current place
    public Place followDirection(String s, Character c){
        for (Direction d : directions) {
            if (d.match(s)) {
                try {
                    return d.follow(c);
                } catch (Direction.LockedDirectionException e){
                    return this;
                }
            }
        }
        return this;
    }

    // Prints debug information about all places
    public static void printAll(){
        for (Place p : places.values()){
            p.print();
        }
    }

    // Prints debug information about this particular place
    public void print(){
        System.out.println(String.format("ID: %d", ID));
        System.out.println(String.format("Name: %s", name));
        System.out.println(String.format("Description: %s", description));
        System.out.println("Directions: ");
        for (Direction d : directions){
            d.print();
        }
        System.out.println("Artifacts: ");
        for (Artifact a : artifacts){
            a.print();
        }
        System.out.println("Characters: ");
        for (Character c : characters) {
            c.print();
        }
    }

    // Provides use with information about the place
    public void display(){
        System.out.println(name);
        System.out.println(description);
        if (artifacts.size() < 1){
            System.out.println("You see no significant artifacts.");
        } else { // Shows the artifacts in the place
            System.out.println("Items you see: ");
            for (Artifact a : artifacts){
                a.display();
            }
        }
    }

    // Checks to see whether this place is an exit
    public boolean isExit(){
        return ID == 1;
    }

    // Returns a random direction within this place
    public String getRandomDirection() {
        try {
            Direction d = (Direction) directions.toArray()[new Random().nextInt(directions.size())];
            return d.getDir();
        } catch (IllegalArgumentException e) {
            return "";
        }
    }
}
