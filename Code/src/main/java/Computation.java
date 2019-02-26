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

        }

    }

    private OPA_Configuration createInitial(){
        return new OPA_Configuration(stack, opa.getInitial(), orig_input + "#");
    }

    private OPA_Configuration nextConfig(){

    }
    private OPA_Configuration getLastConfig(){
        return run.get(run.size() - 1);
    }
}
