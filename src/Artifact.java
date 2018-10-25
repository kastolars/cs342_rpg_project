/*
Author: Karol Stolarski
netID: kstola2

The artifact class is responsible for making and using
all the various items found throughout the game.
Some artifacts are keys, some are immovable.
Keys have keypatterns that can unlock the right door.
 */

import java.util.Random;
import java.util.Scanner;

public class Artifact {
    private int version;
    private int ID;
    private String name;
    private String description;
    private int value;
    private int mobility;
    private int keyPattern;

    public Artifact(Scanner sc, int version){
        description = "";

        // Location ID
        String line = CleanLineScanner.getCleanLine(sc);
        int placeOrCharID = Integer.valueOf(line);

        // Metadata
        line = CleanLineScanner.getCleanLine(sc);
        String[] arr = line.split("\\s+");
        ID = Integer.valueOf(arr[0]);
        value = Integer.valueOf(arr[1]);
        mobility = Integer.valueOf(arr[2]);
        keyPattern = Integer.valueOf(arr[3]);
        name = line.replaceAll("\\d", "").trim();

        // Lines for description
        line = CleanLineScanner.getCleanLine(sc);
        int count = Integer.valueOf(line);

        // Description
        for (int i = 0; i < count; i++){
            description += CleanLineScanner.getCleanLine(sc) + "\n";
        }

        // Place artifact at location
        if (placeOrCharID < 0){
            Character.getCharacterByID(Math.abs(placeOrCharID)).addArtifact(this);
        } else if (placeOrCharID > 0) {
            Place.getPlaceByID(Math.abs(placeOrCharID)).addArtifact(this);
        } else {
            Place p;
            do {
                p = (Place) Place.places.values().toArray()[new Random().nextInt(Place.places.size())];
            } while (p.isExit());
            p.addArtifact(this);
        }
    }

    public void use(Character c, Place p){}

    // Checks to see if the name matches the string given
    public boolean match(String s){
        return name.matches("(?i)" + s);
    }

    // Prints artifact information
    public void print(){
        System.out.println(String.format("ID: %d", ID));
        System.out.println(String.format("Name: %s", name));
        System.out.println(String.format("Description: %s", description));
        System.out.println(String.format("Value: %d", value));
        System.out.println(String.format("Mobility: %d", mobility));
        System.out.println(String.format("KeyPattern: %d", keyPattern));
    }

    // Returns the name of the artifact
    public String name(){
        return name;
    }

    // Displays information for the player
    public void display(){
        System.out.println("<" + name + ">");
        System.out.println(description);
    }

    // Returns the movability of the item
    public int mobility() {
        return mobility;
    }

    // Returns the keypattern of the key
    public int getKeyPattern() {
        return keyPattern;
    }
}
