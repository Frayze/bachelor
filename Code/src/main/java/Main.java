import model.automaton.*;
import auxilary.OPA_WriterReader;
import model.computation.Computation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        //Setting up a test automat
        OP_Matrix opm = new OP_Matrix('s', 'r', 'w', 'u')
                .setEntry('s', Relation.yield, 's')
                .setEntry('s', Relation.equal, 'r')
                .setEntry('s', Relation.yield, 'w')

                .setEntry('r', Relation.takes, 's')
                .setEntry('r', Relation.takes, 'r')
                .setEntry('r', Relation.takes, 'w')
                .setEntry('r', Relation.takes, 'u')

                .setEntry('w', Relation.yield, 's')
                .setEntry('w', Relation.takes, 'r')
                .setEntry('w', Relation.yield, 'w')
                .setEntry('w', Relation.equal, 'u')

                .setEntry('u', Relation.takes, 's')
                .setEntry('u', Relation.takes, 'r')
                .setEntry('u', Relation.takes, 'w')
                .setEntry('u', Relation.takes, 'u');




        State q_0 = new State("q_0");
        State q_1 = new State("q_1");
        State q_2 = new State("q_2");
        State q_a = new State("0");
        State q_b = new State("1");
        State q_c = new State("2");



        Transitions trans = new Transitions();
        trans.addAll(
                new Push_Transition(q_0, 's', q_a),
                new Push_Transition(q_a, 's', q_a),
                new Push_Transition(q_b, 's', q_a),
                new Push_Transition(q_c, 's', q_a),

                new Push_Transition(q_a, 'w', q_b),
                new Push_Transition(q_b, 'w', q_c),
                new Push_Transition(q_a, 'w', q_2),
                new Push_Transition(q_2, 'w', q_2),
                new Push_Transition(q_b, 'w', q_2),
                new Push_Transition(q_c, 'w', q_2),

                new Shift_Transition(q_2, 'u', q_2),
                new Shift_Transition(q_a, 'r', q_1),

                new Pop_Transition(q_0, q_0, q_0),
                new Pop_Transition(q_0, q_a, q_0),
                new Pop_Transition(q_0, q_b, q_0),

                new Pop_Transition(q_a, q_0, q_0),
                new Pop_Transition(q_a, q_a, q_0),
                new Pop_Transition(q_a, q_b, q_0),
                new Pop_Transition(q_a, q_c, q_0),

                new Pop_Transition(q_1, q_0, q_0),
                new Pop_Transition(q_1, q_a, q_a),
                new Pop_Transition(q_1, q_b, q_b),
                new Pop_Transition(q_1, q_c, q_c),

                new Pop_Transition(q_2, q_2, q_2),
                new Pop_Transition(q_2, q_a, q_a),
                new Pop_Transition(q_2, q_b, q_b),
                new Pop_Transition(q_2, q_c, q_c),

                new Pop_Transition(q_b, q_a, q_a),
                new Pop_Transition(q_c, q_b, q_b)
        );

        OP_Automat opa = new OP_Automat.Builder()
                .withTerminalSet('s','w','u', 'r')
                .basedOnMatrix(opm)
                .hasStates(q_0, q_1, q_2, q_a, q_b, q_c)
                .startsAtStates(q_0)
                .acceptsAtStates(q_0)
                .withTransitions(trans)
                .build();

        opa.printAutomat();

        new Computation(opa, "s w u r s w w u s w r w s").compute();

        OPA_WriterReader rw = new OPA_WriterReader();

        rw.saveOPA(opa, "src/main/resources/opa_2.txt");






    }
}
