/*
Author: Karol Stolarski
netID: kstola2

The AI class controls the NPC characters in the game.
A random number in the range or 4 is generated
and then one of 4 legal moves are executed.
Some moves require arguments, and some are not
always available.
 */

import java.util.Random;

public class AI implements DecisionMaker {

    @Override
    public Move getMove(Character c, Place p) {
        int move = new Random().nextInt(4);
        switch (move){
            case 0: // USE Move
                try {
                    Artifact art = c.getRandomArtifact();
                    return new Move("USE", art.name());
                } catch (NullPointerException e){
                    return new Move("USE", "");
                }
            case 1: // GET Move
                try {
                    Artifact inv = p.getRandomArtifact();
                    p.removeArtifactByName(inv.name());
                    return new Move("GET", inv.name());
                } catch (NullPointerException e){
                    return new Move("GET", "");
                }
            case 2:
                try { // DROP Move
                    Artifact trash = c.getRandomArtifact();
                    c.removeArtifact(trash.name());
                    return new Move("DROP", trash.name());
                } catch (NullPointerException e){
                    return new Move("DROP", "");
                }
            case 3: // GO Move
                default:
                    String dir = p.getRandomDirection();
                return new Move("GO", dir);
        }
    }

}
