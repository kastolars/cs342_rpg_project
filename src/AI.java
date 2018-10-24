import java.util.Random;

public class AI implements DecisionMaker {

    @Override
    public Move getMove(Character c, Place p) {
        int move = new Random().nextInt(4);
        switch (move){
            case 0:
                String[] arts = {c.getRandomArtifact()};
                return new Move("USE", arts);
            case 1:
                String[] inv = {p.getRandomArtifact()};
                return new Move("GET", inv);
            case 2:
                String[] trash = {c.getRandomArtifact()};
                return new Move("DROP", trash);
            case 3:
                default:
                    String[] dir = {p.getRandomDirection()};
                return new Move("GO", dir);
        }
    }

}
