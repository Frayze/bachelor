package model.computation;

import model.automaton.State;

public class OPA_Configuration {
    OP_Stack OPStack;
    State state;
    String input;
    int run_pos;

    public OPA_Configuration(OP_Stack OPStack, State q, String input){
        this.OPStack = new OP_Stack(OPStack);
        this.state = q;
        this.input = input;
    }

    /**
     * Printmethod for testing purpose
     */
    public void printConfig(){
    System.out.print(OPStack); System.out.print("   ");
    System.out.print(state); System.out.print("   ");
    System.out.println(input);
    }


    public OP_Stack getOPStack() {
        return OPStack;
    }

    public void setOPStack(OP_Stack OPStack) {
        this.OPStack = OPStack;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }


}
