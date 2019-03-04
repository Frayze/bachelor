package model.automaton;

import java.io.Serializable;

public class Shift_Transition extends Transition implements Serializable {

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
