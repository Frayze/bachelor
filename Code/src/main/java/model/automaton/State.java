package model.automaton;

import java.io.Serializable;

public class State implements Serializable {

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
