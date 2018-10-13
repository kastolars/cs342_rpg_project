import java.util.Scanner;

public class Direction {
    private int ID;
    private DirType dir;
    private Place from;
    private Place to;
    private boolean locked;
    private int lockPattern;

    public Direction(Scanner sc, int version){}

    public void useKey(Artifact a){}

    public Place follow() throws LockedDirectionException {
        if (locked) {
            throw new LockedDirectionException("Door is locked!");
        } else {
            return to;
        }
    }

    public void print(){
        System.out.println(String.format("ID: ", ID));
        System.out.println(String.format("Type: ", dir.toString()));
        System.out.print(String.format("From: "));
        from.print();
        System.out.print(String.format("To: "));
        to.print();
        System.out.println(String.format("Locked: ", locked));
        System.out.println(String.format("Lock Pattern: ", lockPattern));
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
