import java.util.Scanner;

import static java.lang.Math.abs;

public class Direction {
    private int ID;
    private DirType dir;
    private Place from;
    private Place to;
    private boolean locked;
    private int lockPattern;

    public Direction(Scanner sc, int version){
        String line;
        String[] arr;
        line = CleanLineScanner.getCleanLine(sc);
        arr = line.split("\\s+");
        ID = Integer.valueOf(arr[0]);
        int fromID = Integer.valueOf(arr[1]);
        from = Place.getPlaceByID(fromID);
        dir = DirType.valueOf(arr[2]);
        int toID = Integer.valueOf(arr[3]);
        to = Place.getPlaceByID(abs(toID));
        locked = toID < 0;
        lockPattern = Integer.valueOf(arr[4]);
        Place.getPlaceByID(fromID).addDirection(this);
    }

    public void useKey(Artifact a){}

    public Place follow() throws LockedDirectionException {
        if (locked) {
            throw new LockedDirectionException("Door is locked!");
        } else {
            return to;
        }
    }

    public void print(){
        System.out.println(String.format("ID: %d", ID));
        System.out.println(String.format("Type: %s", dir.toString()));
        System.out.println("From: ");
        from.print();
        System.out.println("To: ");
        to.print();
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

    public class LockedDirectionException extends Throwable {
        public LockedDirectionException(String s) {
            System.out.println(s);
        }
    }
}
