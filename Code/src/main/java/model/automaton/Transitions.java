package model.automaton;

import java.util.*;

/**
 * This class will gather all different kinds of Transitions.
 */
public class Transitions {
    Set<Transition> transitions;

    public Transitions(){
        transitions = new HashSet<Transition>();
    }

    /**
     * Finds the defined Transition for the actual Configuration of the Computation.
     * It can find multiple entries, so it returns a List of Transitions
     * @param q
     * @param a_p
     * @return
     */
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

    /**
     * Print-Function for Testing purposes
     */
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
