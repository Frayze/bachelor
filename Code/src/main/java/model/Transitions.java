package model;

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
        Iterator it = transitions.iterator();
        System.out.println("\nTransitions:");

        while (it.hasNext()){
            System.out.println((Transition)it.next().);
            if(it.next() instanceof Push_Transition){
                System.out.println(((Push_Transition) it.next()).getOrigin() + " + " + ((Push_Transition) it.next()).getInput()
                        + " -> " + ((Push_Transition) it.next()).getDestination() + " (Push)");
            }
            if(it.next() instanceof Shift_Transition){
                System.out.println(((Shift_Transition) it.next()).getOrigin() + " + " + ((Shift_Transition) it.next()).getInput()
                        + " -> " + ((Shift_Transition) it.next()).getDestination() + " (Shift)");
            }
            if(it.next() instanceof Pop_Transition){
                System.out.println(((Pop_Transition) it.next()).getOrigin() + " + " + ((Pop_Transition) it.next()).getInput()
                        + " -> " + ((Pop_Transition) it.next()).getDestination() + " (Pop)");
            }
        }
    }
}
