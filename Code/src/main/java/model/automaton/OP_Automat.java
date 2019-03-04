package model.automaton;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OP_Automat implements Serializable {

    // List of terminal Symbols
    private Set<Character> terminals;

    // The Operator Precedence Matrix for the Automat.
    private OP_Matrix matrix;

    // The states of the Automat
    private Set<State> states;

    // Initial state of the Automat
    private Set<State> initial;

    //Accepting States
    private Set<State> accepting;

    // Transition function
    private Transitions transitions;

    // This class contains many Fields, so it will be initialized with the Builder pattern
    // OP_Automat can only be created in such a way
    private OP_Automat(){

    }

    public static class Builder {

        private Set<Character> terminals;

        /**
         * Can be initialized either with a Set of Terminals or with varArg Characters
         * @param terminals
         * @return
         */
        public Builder withTerminalSet(Set<Character> terminals){
            this.terminals = terminals;
                    return this;
        }
        public Builder withTerminalSet(Character...cl){
            this.terminals = new HashSet<Character>();
            for (Character c :cl
                 ) terminals.add(c);
            return this;
        }

        private OP_Matrix matrix;

        public Builder basedOnMatrix(OP_Matrix opm){
            this.matrix = opm;
            return this;
        }

        private Set<State> states;

        /**
         * Can be initialized either with a Set of States or varArg States
         * @param states
         * @return
         */
        public Builder hasStates(Set<State> states){
            this.states = states;
            return this;
        }
        public Builder hasStates(State... st){
            this.states = new HashSet<State>();
            for (State s: st
                 ) states.add(s);
            return this;
        }

        private Set<State> initial;

        public Builder startsAtStates(Set<State> initial){
            this.initial = initial;
            return this;
        }

        public Builder startsAtStates(State... st){
            this.initial = new HashSet<State>();
            for (State s: st
            ) initial.add(s);
            return this;
        }

        private Set<State> accepting;

        public Builder acceptsAtStates(Set<State> accepting){
            this.accepting = accepting;
            return this;
        }

        public Builder acceptsAtStates(State... as){
            this.accepting = new HashSet<State>();
            for (State a: as
                 ) accepting.add(a);
            return this;
        }

        public Transitions transitions;

        public Builder withTransitions(Transitions trans){
            this.transitions = trans;
            return this;
        }

        /**
         * Final build Method
         * @return the created OPA;
         */
        public OP_Automat build(){
            OP_Automat opa = new OP_Automat();
            opa.terminals = this.terminals;
            opa.matrix = this.matrix;
            opa.states = this.states;
            opa.initial = this.initial;
            opa.accepting = this.accepting;
            opa.transitions = this.transitions;

            return opa;
        }

    }

    /**
     * Main Function to test for Consistency. Calls some partially tests
     * @return
     */
    public boolean checkConsistency(){
        return this.matrix.checkMatrixSize() &&
               this.checkSpecialStates();
    }


    /**
     * Checks for Special States
     * @return
     */
    private boolean checkSpecialStates(){
        if(this.initial == null ){
            System.out.println("No Initial State set");
            return false;
        }

        if(this.accepting == null || this.accepting.isEmpty()){
            System.out.println("No Accepting State set");
            return false;
        }

        if(!this.states.contains(this.initial)){
            System.out.println("State Set doesnt contain the Initial state");
            return false;
        }

        for (State f: this.accepting
             ) {
            if(!this.states.contains(f)){
                System.out.println("State Set doesnt contain the Accepting State");
                return false;
            }
        }

        System.out.println("Initial and Accepting States properly set");
        return true;
    }

    /**
     * Print Method for Testing purposes
     */
    public void printAutomat(){
        System.out.println("Terminal Symbols: ");
        for(Character c : this.terminals){
            System.out.print(c + ", ");
        }
        System.out.println("\n");

        this.getMatrix().printMatrix();

        System.out.println("\nStates: ");
        for(State q: this.states) System.out.print(q.getName() + ", ");

        System.out.println("\nInitial State: ");
        for(State q: this.initial){
            System.out.print(q.getName() + ", ");
        }
        System.out.print("Accepting States: ");
        for(State q: this.accepting){
            System.out.print(q.getName() + ", ");
        }
        transitions.printTransitions();

    }

    /*
    Getter and Setter Functions
     */

    public Set<Character> getTerminals() {
        return terminals;
    }

    // Maybe an unwanted Funtion
    public void setTerminals(Set<Character> terminals) {
        this.terminals = terminals;
    }

    public OP_Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(OP_Matrix matrix) {
        this.matrix = matrix;
    }

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

    public Set<State> getInitial() { return initial; }

    public void setInitial(Set<State> initial) {
        this.initial = initial;
    }

    public Set<State> getAccepting() {
        return accepting;
    }

    public void setAccepting(Set<State> accepting) {
        this.accepting = accepting;
    }

    public Transitions getTransitions() { return transitions; }

    public void setTransitions(Transitions transitions) {
        this.transitions = transitions;
    }

}
