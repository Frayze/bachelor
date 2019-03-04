package model.automaton;

public class Push_Transition extends Transition {

    private Character input;

    /**
     * Push Transition: Q X delta -> Q
     * @param origin
     * @param input
     * @param destination
     */
    public Push_Transition(State origin, Character input, State destination){
        super(origin, destination);
        this.input = input;

    }

    @Override
    public Character getInput() {
        return input;
    }
}
