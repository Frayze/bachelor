import model.*;

import java.util.LinkedList;
import java.util.List;

public class Computation {
    private OP_Automat opa;
    private Stack stack;
    private List<OPA_Configuration> run;
    private String orig_input;

    public Computation(OP_Automat opa, String input){
        this.opa = opa;
        this.stack = new Stack();
        this.run = new LinkedList<OPA_Configuration>();
        this.orig_input = input;
    }

    public void compute(){
        run.add(createInitial());

        while
        (!(getLastConfig().getStack().isEmpty() &&
        getLastConfig().getInput().equals("#") &&
        opa.getAccepting().contains(getLastConfig().getState()))) {
            nextConfig();

        }

    }

    private OPA_Configuration createInitial(){
        return new OPA_Configuration(stack, opa.getInitial(), orig_input + "#");
    }

    private OPA_Configuration nextConfig(){
        OPA_Configuration config;
        //Finds the entry in the OPM to decide which move has to be made. Depends on the Topstacksymbol and first Input symbol
        switch(opa.getMatrix().getEntry(stack.getTopSymbol(), getActualInput().charAt(0))){
            case equal:
                // Finds all defined transitions based on the actual state and actual input
                for (Transition trans: opa.getTransitions().findSpecificTransition(getActualState(), getActualInput())
                     ) {
                    //Chooses the Transition with a fitting type
                    if(trans instanceof Shift_Transition){
                        stack.changeTopSymbol(getActualInput().charAt(0));
                        State r = trans.getDestination();
                        String temp_input = getActualInput().substring(1);
                        config = new OPA_Configuration(stack, r, temp_input);
                        System.out.println("\nCreated new Configuration From Shift- Move");
                        break;


                    }

                }
            case takes:
                // Finds all defined transitions based on the actual state and top stack state
                for(Transition trans: opa.getTransitions().findSpecificTransition(getActualState(), stack.getTopState())) {
                    //Only Pop-Moves can be found with two states as parameter
                    stack.removeFromStack();
                    State q = trans.getDestination();

                    config = new OPA_Configuration(stack, q, getActualInput());
                    System.out.println("\nCreated new Configuration From Pop- Move");
                }
                break;

            case yield:
                // Finds all defined transitions based on the actual state and actual input
                for(Transition trans: opa.getTransitions().findSpecificTransition(getActualState(), getActualInput())){
                    //Chooses the Transition with a fitting type
                    if(trans instanceof  Push_Transition){
                        stack.addToStack( getActualInput().charAt(0), getActualState());
                        State q = trans.getDestination();
                        String temp_input = getActualInput().substring(1);
                        config = new OPA_Configuration(stack, q, temp_input);
                        System.out.println("\nCreated new Configuration From Push- Move");
                    }
                }

            default:
                break;
    }
    return null;
    }
    private OPA_Configuration getLastConfig(){
        return run.get(run.size() - 1);
    }

    private State getActualState(){
        return getLastConfig().getState();
    }

    private String getActualInput(){
        return getLastConfig().getInput();
    }
}
