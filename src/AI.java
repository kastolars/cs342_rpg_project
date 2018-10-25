import java.util.Random;

public class AI implements DecisionMaker {

    @Override
    public Move getMove(Character c, Place p) {
        int move = new Random().nextInt(4);
        switch (move){
            case 0:
                try {
                    Artifact art = c.getRandomArtifact();
                    return new Move("USE", art.name());
                } catch (NullPointerException e){
                    return new Move("USE", "");
                }
            case 1:
                try {
                    Artifact inv = p.getRandomArtifact();
                    return new Move("GET", inv.name());
                } catch (NullPointerException e){
                    return new Move("GET", "");
                }
            case 2:
                try {
                    Artifact trash = c.getRandomArtifact();
                    return new Move("DROP", trash.name());
                } catch (NullPointerException e){
                    return new Move("DROP", "");
                }
            case 3:
                default:
                    String dir = p.getRandomDirection();
                return new Move("GO", dir);
        }
    }

}
