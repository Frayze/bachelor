package model;

import org.javatuples.Pair;

public class Stack {

    private java.util.Stack<Pair<Character, State>> stack;

    public Stack(){
    stack = new java.util.Stack<Pair<Character, State>>();
    }

    public void addToStack(Pair<Character, State> elem){
        stack.push(elem);
    }
    public void addToStack(Character c, State q){
        stack.push(new Pair<Character, State>(c, q));
    }

    public void removeFromStack(){
        stack.pop();
    }

    public void changeTopSymbol(Character c){
        Pair<Character, State> tmp = new Pair<Character, State>(c, stack.peek().getValue1());
        stack.pop();
        stack.push(tmp);
    }

    public Character getTopSymbol(){
        if(stack.empty()) return '#';
        else return stack.peek().getValue0();
    }

    public State getTopState(){
        return stack.peek().getValue1();
    }
    public boolean isEmpty(){
        return stack.isEmpty();
    }
}
