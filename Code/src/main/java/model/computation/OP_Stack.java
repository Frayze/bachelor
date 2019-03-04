package model.computation;

import model.automaton.State;
import org.javatuples.Pair;

/**
 * Simple definition for the OPStack. Extends the Basic functions with some OPA related functions
 */
public class OP_Stack {

    private java.util.Stack<Pair<Character, State>> stack;

    public OP_Stack(OP_Stack copy){
        this.stack = new java.util.Stack<Pair<Character, State>>();
        stack.addAll(copy.stack);
    }
    public OP_Stack(){
    stack = new java.util.Stack<Pair<Character, State>>();
    }

    /**
     * Basic add Function with Pair
     * @param elem
     */
    public void addToStack(Pair<Character, State> elem){
        stack.push(elem);
    }

    /**
     * Basic add Function with Character and State, Pair will be created inside
     * @param c
     * @param q
     */
    public void addToStack(Character c, State q){
        stack.push(new Pair<Character, State>(c, q));
    }

    /**
     * Basic pop Function
     */
    public void removeFromStack(){
        stack.pop();
    }

    /**
     * Changes the top Symbol (Shift-Transition)
     * @param c
     */
    public void changeTopSymbol(Character c){
        Pair<Character, State> tmp = new Pair<Character, State>(c, stack.peek().getValue1());
        stack.pop();
        stack.push(tmp);
    }

    /**
     * Get TopSymbol or the '#' if the OPStack is empty
     * @return
     */
    public Character getTopSymbol(){
        if(stack.empty()) return '#';
        else return stack.peek().getValue0();
    }

    /**
     * Get Top State (Pop-Function)
     * @return
     */
    public State getTopState(){
        return stack.peek().getValue1();
    }


    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public String toString(){
        String tmp = "#";
        for (Pair<Character, State> p: this.stack
             ) {
            tmp = tmp.concat("[" + p.getValue0() + "," + p.getValue1() + "] ");
        }
        return tmp;
    }



}
