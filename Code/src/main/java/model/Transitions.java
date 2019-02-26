package model;

import sun.awt.Symbol;

import java.util.*;


public class Transitions {
    Set<Transition> transitions;

    public Transitions(){
        transitions = new HashSet<Transition>();
    }

    public List<Transition> findSpecificTransition(State q, Object a_p){
        List<Transition> lst = new ArrayList<Transition>();
        Iterator it = transitions.iterator();
        while (it.hasNext()){
        Transition temp = (Transition) it.next();
        if(temp.getOrigin().equals(q) && temp.getInput().equals(a_p)){
            lst.add(temp);
        }
        }
       return lst;
    }
    public void add(Transition tr){
        transitions.add(tr);
    }

    public void printTransitions(){
        Iterator<Transition> it = transitions.iterator();
        System.out.println("\n\n Transitions: \n" );

        while (it.hasNext()){
           Transition temp = it.next();
           if(temp instanceof Push_Transition){
                System.out.println(temp.getOrigin() + " + " + temp.getInput()
                        + " -> " +  temp.getDestination() + " (Push)");
            }
            if(temp instanceof Shift_Transition){
                System.out.println( temp.getOrigin() + " + " + temp.getInput()
                        + " -> " + temp.getDestination() + " (Shift)");
            }
            else if(temp instanceof Pop_Transition){
                System.out.println(temp.getOrigin() + " + " + temp.getInput()
                        + " -> " + temp.getDestination() + " (Pop)");
            }

        }
    }
}
