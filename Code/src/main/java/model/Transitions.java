package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Transitions {
    Set<Transition> transitions;

    public Transitions(){
        transitions = new HashSet<Transition>();
    }

    public State move(State q, Object a_p, Class type){


        Iterator it = transitions.iterator();
        while (it.hasNext()){
        Transition temp = (Transition) it.next();

        if(temp.getOrigin().equals(q) && temp.getInput().equals(a_p)){
            return temp.getDestination();
        }
        }
       return null;
    }
}
