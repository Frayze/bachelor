package model;

public class Pop_Transition extends Transition {

    private State input;

    public Pop_Transition(State origin, State input, State destination){
        super(origin, destination);
        this.input = input;
    }

    @Override
    public State getInput() {
        return input;
    }
}
