import model.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        //Setting up a test automat
        Set<Character> terminals = new HashSet<Character>();
        terminals.add('+');
        terminals.add('*');
        terminals.add('(');
        terminals.add(')');
        terminals.add('n');

        OP_Matrix opm = new OP_Matrix(terminals);
        opm.setEntry('+', Relation.takes, '+'); opm.setEntry('+', Relation.yield, '*');
        opm.setEntry('*', Relation.takes, '+'); opm.setEntry('*', Relation.takes, '*');
        opm.setEntry('(', Relation.yield, '+'); opm.setEntry('(', Relation.yield, '*');
        opm.setEntry(')', Relation.takes, '+'); opm.setEntry(')', Relation.takes, '*');
        opm.setEntry('n', Relation.takes, '+'); opm.setEntry('n', Relation.takes, '*');

        opm.setEntry('+', Relation.yield, '('); opm.setEntry('+', Relation.takes, ')');
        opm.setEntry('*', Relation.yield, '('); opm.setEntry('*', Relation.takes, ')');
        opm.setEntry('(', Relation.yield, '('); opm.setEntry('(', Relation.equal, ')');
                                                        opm.setEntry(')', Relation.takes, ')');
                                                        opm.setEntry('n', Relation.takes, ')');

        opm.setEntry('+', Relation.yield, 'n');
        opm.setEntry('*', Relation.yield, 'n');
        opm.setEntry('(', Relation.yield, 'n');

        List<State> states = new ArrayList<State>();
        states.add(new State("q0"));
        states.add(new State("q1"));
        states.add(new State("q2"));
        states.add(new State("q3"));

        State i_state = states.get(0);

        List<State>  f_states = new ArrayList();
        f_states.add(states.get(1));
        f_states.add(states.get(3));


        Transitions trans = new Transitions();
        trans.add(new Push_Transition(states.get(0), 'n', states.get(1)));
        trans.add(new Push_Transition(states.get(1), '+', states.get(0)));
        trans.add(new Push_Transition(states.get(1), '*', states.get(0)));

        trans.add(new Push_Transition(states.get(0), '(', states.get(2)));
        trans.add(new Push_Transition(states.get(2), '(', states.get(2)));

        trans.add(new Push_Transition(states.get(2), 'n', states.get(3)));
        trans.add(new Push_Transition(states.get(3), '*', states.get(2)));
        trans.add(new Push_Transition(states.get(3), '+', states.get(2)));

        trans.add(new Shift_Transition(states.get(3), ')', states.get(3)));

        trans.add(new Pop_Transition(states.get(1), states.get(0), states.get(1)));
        trans.add(new Pop_Transition(states.get(1), states.get(1), states.get(1)));

        trans.add(new Pop_Transition(states.get(3), states.get(0), states.get(3)));
        trans.add(new Pop_Transition(states.get(3), states.get(1), states.get(3)));
        trans.add(new Pop_Transition(states.get(3), states.get(2), states.get(3)));
        trans.add(new Pop_Transition(states.get(3), states.get(3), states.get(3)));

        OP_Automat opa = new OP_Automat(terminals, opm, states, i_state, f_states, trans);

        opa.printAutomat();

        opa.getTransitions().printTransitions();

        Computation comp = new Computation(opa, "n+n*(n+n)");
        comp.compute();
    }
}
