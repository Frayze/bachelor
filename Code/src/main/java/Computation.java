import model.*;

import java.util.LinkedList;
import java.util.List;

import static java.lang.System.exit;

/**
 * Computation class takes an formerly defined OPA and an input String and creates the run based
 * on the defined transitions
 */
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

    /**
     * Mainmethod to start the Computation
     */
    public void compute(){
        run.add(createInitial());
        System.out.println("\nInitial Configuration successfull");
        while
        (!(getLastConfig().getStack().isEmpty() &&
        getLastConfig().getInput().equals("#") &&
        opa.getAccepting().contains(getLastConfig().getState()))) {
            nextConfig();
        }
        System.out.println("Computation successfull");
    }

    /**
     * Auxilary Method to create initial computation
     * @return
     */
    private OPA_Configuration createInitial(){
        return new OPA_Configuration(stack, opa.getInitial(), orig_input + "#");
    }

    /**
     * Creates the next configuration and adds it to the run.
     * Computation is based on formerly added Configurations
     */
    private void nextConfig(){
        OPA_Configuration config = null;
        //System.out.println(opa.getMatrix().getEntry(stack.getTopSymbol(), getActualInput().charAt(0)));
        //Finds the entry in the OPM to decide which move has to be made. Depends on the Topstacksymbol and first Input symbol
        switch(opa.getMatrix().getEntry(stack.getTopSymbol(), getActualInput().charAt(0))){
            case equal:
                // Finds all defined transitions based on the actual state and actual input
                for (Transition trans: opa.getTransitions().findSpecificTransition(getActualState(), getActualInput().charAt(0))
                     ) {
                    //Chooses the Transition with a fitting type
                    if(trans instanceof Shift_Transition){
                        stack.changeTopSymbol(getActualInput().charAt(0));
                        State q = trans.getDestination();
                        String temp_input = getActualInput().substring(1);
                        config = new OPA_Configuration(stack, q, temp_input);
                        System.out.println("\nCreated new Configuration From Shift- Move");
                        break;
                    }
                }
                break;

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
                //System.out.println(opa.getTransitions().findSpecificTransition(getActualState(), getActualInput()));
                for(Transition trans: opa.getTransitions().findSpecificTransition(getActualState(), getActualInput().charAt(0))){
                    //Chooses the Transition with a fitting type
                    if(trans instanceof  Push_Transition){
                        stack.addToStack( getActualInput().charAt(0), getActualState());
                        State q = trans.getDestination();
                        String temp_input = getActualInput().substring(1);
                        config = new OPA_Configuration(stack, q, temp_input);
                        System.out.println("\nCreated new Configuration From Push- Move");
                    }
                }
                break;
    }

    if(config != null){run.add(config); config.printConfig();}
    else {System.out.println("Error while creating Configuration"); exit(0);}
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
