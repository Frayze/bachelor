package model.automaton;

public class State {

    private String name;

    /**
     * Basic definition for the state.
     */
    public State(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return this.name;
    }
}
