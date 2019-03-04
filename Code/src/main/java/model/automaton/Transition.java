package model.automaton;


/**
 * Abstract Class for Transitions
 * A Transition is a Triple defined by: Q X (Q U Sigma) X Q
 */
public abstract class Transition {

    private State origin;

    private State destination;

    /**
     * Standardconstructor for Transition type with origin and destination state. The third part of the triplet (inputt)
     * will be defined in inheriting Classes.
     * @param origin
     * @param destination
     */
    public Transition(State origin, State destination){
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * Input Type variates between Shift- Push- Pop-Transitions and can either be a [Character] or a [State].
     * Transition Types inheriting from this must implement it properly
     * @return the Input
     */
    public abstract Object getInput();


    /**
     * Origin method is the same for all Transition types
     * @return
     */
    public State getOrigin(){
        return origin;
    }

    /**
     * Destination method is the same for all Transition types
     * @return
     */
    public State getDestination(){
        return destination;
    }
}
