package model;

public class Push_Transition extends Transition {

    private Character input;

    public Push_Transition(State origin, Character input, State destination){
        super(origin, destination);
        this.input = input;

    }

    @Override
    public Character getInput() {
        return input;
    }
}
