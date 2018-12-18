import model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //Setting up a test automat
        List<Character> terminals = new ArrayList<>();
        terminals.add('n');
        terminals.add('+');
        terminals.add('*');
        terminals.add('(');
        terminals.add(')');

        OP_Matrix opm = new OP_Matrix(terminals);
        opm.setEntry('n', Relation.yield, '+');
        opm.setEntry('(', Relation.equal, ')');

        List<State> states = new ArrayList<State>();
        states.add(new State("q0"));
        states.add(new State("q1"));
        states.add(new State("q2"));
        states.add(new State("q3"));

        State i_state = states.get(0);

        List<State>  f_states = new ArrayList<>();
        f_states.add(states.get(1));
        f_states.add(states.get(3));

        OP_Automat opa = new OP_Automat(terminals, opm, states, i_state, f_states);

        opa.printAutomat();
    }
}
