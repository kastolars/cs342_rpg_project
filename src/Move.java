import java.util.Arrays;

public class Move {
    private MoveType type;
    private String argument;

    public Move(String action, String argument) {
        this.type = MoveType.valueOf(action.toUpperCase());
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
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
