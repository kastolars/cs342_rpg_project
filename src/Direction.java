import java.util.Scanner;

import static java.lang.Math.abs;

public class Direction {
    private int ID;
    private DirType dir;
    private Place source;
    private Place destination;
    private boolean locked;
    private int lockPattern;

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

    public void useKey(Artifact a){}

    public Place follow() throws LockedDirectionException {
        if (locked) {
            throw new LockedDirectionException("Door is locked!");
        } else {
            return destination;
        }
    }

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

    public boolean match(String s){
        return dir.match(s);
    }

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

        public String toString(){
            return text;
        }

        private boolean match(String s) {
            return s.matches("(?i)" + text + "|" + abbreviation);
        }
    }

    public String getDir() {
        return dir.toString();
    }

    public class LockedDirectionException extends Throwable {
        public LockedDirectionException(String s) {
            System.out.println(s);
        }
    }
}
