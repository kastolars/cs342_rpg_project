/*
Author: Karol Stolarski
netID: kstola2

Interface that Abstracts User Interface
and AI.
 */

public interface DecisionMaker {

    public Move getMove(Character c, Place p);
}
