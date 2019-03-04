package model.automaton;

import java.io.Serializable;

public class Pop_Transition extends Transition implements Serializable {

    private State input;

    /**
     * Pop Transition: Q X Q -> Q
     * @param origin
     * @param input
     * @param destination
     */
    public Pop_Transition(State origin, State input, State destination){
        super(origin, destination);
        this.input = input;
    }

    @Override
    public State getInput() {
        return input;
    }
}
