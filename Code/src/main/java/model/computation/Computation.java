package model.computation;

import model.automaton.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.exit;

/**
 * Computation class takes an formerly defined OPA and an input String and creates the run based
 * on the defined transitions
 */
public class Computation {

    private OP_Automat opa;

    private List<OPA_Configuration> run;

    private String orig_input;

    private List<OPA_Configuration> waiting;


    public Computation(OP_Automat opa, String input){
        this.opa = opa;
        this.run = new LinkedList<OPA_Configuration>();
        this.orig_input = input.replaceAll(" ", "");
    }

    /**
     * Mainmethod to start the Computation
     */
    public void compute(){
        //Nondeterministic Computation of the OPA
        //Starts the actual run and adds further initial states to the waiting configurations
        createInitial();
        System.out.println("\nInitial Configuration successfull");


        while (!(waiting.isEmpty() && getLastConfig().getInput().equals("#"))){
            OPA_Configuration actual = getLastConfig();
            if(isAcceptingRun()){
                break;
            }
            else{
                System.out.println("Error with run ");
                if(waiting.size() > 0)
                removeUntilWaitingConfig();
                else{
                    System.out.println("No path available");
                    exit(0);
                }
            }

        }

        for(OPA_Configuration c: run) c.printConfig();
        System.out.println("Computation successfull");

    }

    /**
     * This Method will reset the Configuration run to its highest waiting configuration,
     * which will then go ahead with the run
     */
    private void removeUntilWaitingConfig(){
        int index = waiting.get(waiting.size()-1).run_pos;
        for(int i = run.size()-1; i > 0; i--){
            if(run.get(i).run_pos == index){
                run.remove(run.size() - 1 );
                run.add(waiting.get(waiting.size() -1));
                waiting.remove(waiting.size() - 1);
                break;
            }
            else {
                run.remove(run.size() - 1);
            }
        }



    }

    /**
     * This method tries to compute an accepting Run based on its current latest Config.
     * Returns false if no further Path is available
     * @return
     */
    private boolean isAcceptingRun(){
        boolean accept = true;
        while
        (!(getLastConfig().getOPStack().isEmpty() &&
                getLastConfig().getInput().equals("#") &&
                opa.getAccepting().contains(getLastConfig().getState()))) {
            if(nextConfig()){
            }
            else{
                return false;
            }
        }
        return accept;
    }
    /**
     * Auxilary Method to create initial computation
     * @return
     */
    private void createInitial(){
        waiting = new ArrayList<OPA_Configuration>();
        for (State i: opa.getInitial()
             ) {
            OPA_Configuration C = new OPA_Configuration(new OP_Stack(), i, orig_input + "#");
            C.run_pos = 0;
            this.waiting.add(C);
        }
        run.add(waiting.get(waiting.size() - 1));
        waiting.remove(waiting.size()- 1);

    }

    /**
     * Creates the next configuration and adds it to the run.
     * Computation is based on formerly added Configurations
     */
    private boolean nextConfig() {
        //Will contain all new possible next Configurations.
        List<OPA_Configuration> new_config = new ArrayList<OPA_Configuration>();
        OP_Stack newStack;
        //System.out.println(opa.getMatrix().getEntry(OPStack.getTopSymbol(), getActualInput().charAt(0)));
        //Finds the entry in the OPM to decide which move has to be made. Depends on the Topstacksymbol and first Input symbol
        switch (opa.getMatrix().getEntry(getLastConfig().OPStack.getTopSymbol(), getActualInput().charAt(0))) {
            case equal:
                // Finds all defined transitions based on the actual state and actual input
                for (Transition trans : opa.getTransitions().findSpecificTransition(getActualState(), getActualInput().charAt(0))
                ) {
                    //Chooses the Transition with a fitting type
                    if (trans instanceof Shift_Transition) {
                        newStack = new OP_Stack(getLastConfig().OPStack);
                        newStack.changeTopSymbol(getActualInput().charAt(0));
                        State q = trans.getDestination();
                        String temp_input = getActualInput().substring(1);
                        OPA_Configuration c = new OPA_Configuration(newStack, q, temp_input);
                        c.run_pos = getLastConfig().run_pos+1;
                        new_config.add(c);
                        System.out.println("Created new Configuration From Shift- Move");
                    }
                }
                break;

            case takes:
                // Finds all defined transitions based on the actual state and top OPStack state
                for (Transition trans : opa.getTransitions().findSpecificTransition(getActualState(), getLastConfig().OPStack.getTopState())) {
                    //Only Pop-Moves can be found with two states as parameter
                    newStack = new OP_Stack(getLastConfig().OPStack);
                    newStack.removeFromStack();
                    State q = trans.getDestination();
                    OPA_Configuration c = new OPA_Configuration(newStack, q, getActualInput());
                    c.run_pos = getLastConfig().run_pos+1;
                    System.out.println("Created new Configuration From Pop- Move");
                   new_config.add(c);
                }
                break;

            case yield:
                // Finds all defined transitions based on the actual state and actual input
                //System.out.println(opa.getTransitions().findSpecificTransition(getActualState(), getActualInput()));
                for (Transition trans : opa.getTransitions().findSpecificTransition(getActualState(), getActualInput().charAt(0))) {
                    //Chooses the Transition with a fitting type
                    if (trans instanceof Push_Transition) {
                        newStack = new OP_Stack(getLastConfig().OPStack);
                        newStack.addToStack(getActualInput().charAt(0), getActualState());
                        State q = trans.getDestination();
                        String temp_input = getActualInput().substring(1);
                        OPA_Configuration c = new OPA_Configuration(newStack, q, temp_input);
                        c.run_pos = getLastConfig().run_pos+1;
                        System.out.println("Created new Configuration From Push- Move");
                        new_config.add(c);
                    }
                }
                break;
        }

        //The run will continue with only one Config, the others are waiting.
        if (!new_config.isEmpty()) {
            run.add(new_config.get(new_config.size() - 1)); new_config.remove(new_config.size() - 1); waiting.addAll(new_config);
            return true;
        } else {
            System.out.println("Error while creating Configuration");
            return false;
        }
    }

    /*
    Auxilary Methods
     */
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
