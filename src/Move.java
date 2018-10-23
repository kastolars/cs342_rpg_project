public class Move {
    private MoveType type;
    private String argument;

    public enum MoveType {
        GO("GO"),
        LOOK("LOOK"),
        QUIT("QUIT"),
        EXIT("EXIT"),
        INVENTORY("INVENTORY"),
        USE("USE"),
        GET("GET"),
        DROP("DROP");

        private final String action;

        MoveType(String s){
            action = s;
        }

        public String toString() {return action;}

        private boolean match(String s) { return s.matches("(?i)" + action);}
    }
}
