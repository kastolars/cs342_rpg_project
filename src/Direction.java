/*
Author: Karol Stolarski
netID: kstola2

The direction basically represents a portal
between two locations. Directions can be locked
and will require a key on occasion to unlock them.
 */


import java.util.Scanner;

import static java.lang.Math.abs;

public class Direction {
    private int ID;
    private DirType dir;
    private Place source;
    private Place destination;
    private boolean locked;
    private int lockPattern;

    // Construct the Direction with the scanner
    public Direction(Scanner sc, int version){
        String line = CleanLineScanner.getCleanLine(sc);
        String[] arr = line.split("\\s+"); // Will contain split up clean line
        ID = Integer.valueOf(arr[0]);
        int sourceID = Integer.valueOf(arr[1]);
        source = Place.getPlaceByID(sourceID);
        dir = DirType.valueOf(arr[2]);
        int destinationID = Integer.valueOf(arr[3]);
        destination = Place.getPlaceByID(abs(destinationID));
        locked = destinationID < 0; // locked if ID of destination is negative
        lockPattern = Integer.valueOf(arr[4]);
        Place.getPlaceByID(sourceID).addDirection(this);
    }

    // Use a key provided on the direction
    public void useKey(Artifact a, Character c){
        if (a.getKeyPattern() == lockPattern){
            locked = !locked; // if the key fits, the direction is unlocked
            if (c instanceof  Player){
                System.out.println("Key works!"); // only print if character is a player
            }
        } else {
            if ( c instanceof Player){
                System.out.println("That doesn't work here.");
            }
        }
    }

    // Attempt to follow the direction. If its locked, a custom exception is thrown
    public Place follow(Character c) throws LockedDirectionException {
        if (locked) {
            throw new LockedDirectionException("Door is locked!", c);
        } else {
            return destination;
        }
    }

    // Prints all debug information associated with the direction
    public void print(){
        System.out.println(String.format("ID: %d", ID));
        System.out.println(String.format("Type: %s", dir.toString()));
        System.out.println("Source: ");
        source.print();
        System.out.println("Destination: ");
        destination.print();
        System.out.println(String.format("Locked: %b", locked));
        System.out.println(String.format("Lock Pattern: %d", lockPattern));
    }

    // Calls the enum class' match function
    public boolean match(String s){
        return dir.match(s);
    }

    // Enum class that governs the naming of the direction
    private enum DirType{
        NONE("None", "None"),
        N("North", "N"),
        S("South", "S"),
        E("East", "E"),
        W("West", "W"),
        U("Up", "U"),
        D("Down", "D"),
        NE("Northeast", "NE"),
        NW("Northwest", "NW"),
        SE("Southeast", "SE"),
        SW("Southwest", "SW"),
        NNE("North-Northeast", "NNE"),
        NNW("North-Northwest", "NNW"),
        ENE("East-Northeast", "ENE"),
        WNW("West-Northwest", "WNW"),
        ESE("East-Southeast", "ESE"),
        WSW("West-Southwest", "WSW"),
        SSE("South-Southeast", "SSE"),
        SSW("South-Southwest", "SSW");

        private final String text;
        private final String abbreviation;

        DirType(String text, String abbreviation) {
            this.text = text;
            this.abbreviation = abbreviation;
        }

        // Returns the direction's name
        public String toString(){
            return text;
        }

        // Checks if the direction matches the string given
        private boolean match(String s) {
            return s.matches("(?i)" + text + "|" + abbreviation);
        }
    }

    // Calls the enum class' toString function
    public String getDir() {
        return dir.toString();
    }

    // Custom exception prints out error message if character is a player
    public class LockedDirectionException extends Throwable {
        public LockedDirectionException(String s, Character c) {
            if ( c instanceof Player){
                System.out.println(s);
            }
        }
    }
}
