package model;

public class OPA_Configuration {

    Stack stack;
    State state;
    String input;

    public OPA_Configuration(Stack stack, State q, String input){
        this.stack = stack;
        this.state = q;
        this.input = input;
    }

    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
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
