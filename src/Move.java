public class Move {
    private MoveType type;
    private String[] arguments;

    public Move(String action, String[] arguments) {
        this.type = MoveType.valueOf(action.toUpperCase());
        this.arguments = arguments;
    }

    public String[] getArguments() {
        return arguments;
    }

    public MoveType getType() {
        return type;
    }

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
    }
}
