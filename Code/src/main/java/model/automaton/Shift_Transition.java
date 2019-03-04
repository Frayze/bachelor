package model.automaton;

public class Shift_Transition extends Transition {

    private Character input;

    /**
     * Shift Transition: Q X delta -> Q
     * @param origin
     * @param input
     * @param destination
     */
    public Shift_Transition(State origin, Character input, State destination){
        super(origin, destination);
        this.input = input;

    }

    @Override
    public Character getInput() {
        return input;
    }
}
