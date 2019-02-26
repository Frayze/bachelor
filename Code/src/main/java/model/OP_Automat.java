package model;

import java.util.ArrayList;
import java.util.List;

public class OP_Automat {

    // List of terminal Symbols
    private List<Character> terminals;

    // The Operator Precedence Matrix for the Automat.
    private OP_Matrix matrix;

    // The states of the Automat
    private List<State> states;

    // Initial state of the Automat
    private State initial;

    //Accepting States
    private List<State> accepting;

    // Transition function
    private Transitions transitions;
    // private Transition transition;

    private Stack stack;


    public OP_Automat(List<Character> terminals,
                           OP_Matrix matrix,
                           List<State> states,
                           State initial,
                           List<State> accepting
                           //,
    ){
        this.terminals = terminals;
        this.matrix = matrix;
        this.states = states;
        this.initial = initial;
        this.accepting = accepting;


    }

    public boolean checkConsistency(){
        return this.matrix.checkMatrixSize() &&
               this.checkSpecialStates();
    }


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

    public void printAutomat(){
        System.out.println("Terminal Symbols: ");
        for(Character c : this.terminals){
            System.out.print(c + ", ");
        }
        System.out.println("\n");

        this.getMatrix().printMatrix();

        System.out.println("\nStates: ");
        for(State q: this.states) System.out.print(q.getName() + ", ");

        System.out.println("\nInitial State: " + this.getInitial().getName());

        System.out.print("Accepting States: ");
        for(State q: this.accepting){
            System.out.print(q.getName() + ", ");
        }
    }

    /*
    Getter and Setter Functions
     */

    public List<Character> getTerminals() {
        return terminals;
    }

    // Maybe an unwanted Funtion
    public void setTerminals(List<Character> terminals) {
        this.terminals = terminals;
    }

    public OP_Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(OP_Matrix matrix) {
        this.matrix = matrix;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public State getInitial() {
        return initial;
    }

    public void setInitial(State initial) {
        this.initial = initial;
    }

    public List<State> getAccepting() {
        return accepting;
    }

    public void setAccepting(List<State> accepting) {
        this.accepting = accepting;
    }
}
