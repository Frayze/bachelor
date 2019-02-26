package model;

public class Shift_Transition extends Transition {

    private Character input;
    public Shift_Transition(State origin, Character input, State destination){
        super(origin, destination);
        this.input = input;

    }

    @Override
    public Character getInput() {
        return input;
    }
}
