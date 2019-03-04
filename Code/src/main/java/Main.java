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
        OP_Matrix opm = new OP_Matrix('+', '*', '(', ')', 'n')
                .setEntry('+', Relation.takes, '+')
                .setEntry('+', Relation.yield, '*')
                .setEntry('+', Relation.yield, '(')
                .setEntry('+', Relation.takes, ')')
                .setEntry('+', Relation.yield, 'n')


                .setEntry('*', Relation.takes, '+')
                .setEntry('*', Relation.takes, '*')
                .setEntry('*', Relation.yield, '(')
                .setEntry('*', Relation.takes, ')')
                .setEntry('*', Relation.yield, 'n')

                .setEntry('(', Relation.yield, '+')
                .setEntry('(', Relation.yield, '*')
                .setEntry('(', Relation.yield, '(')
                .setEntry('(', Relation.equal, ')')
                .setEntry('(', Relation.yield, 'n')

                .setEntry(')', Relation.takes, '+')
                .setEntry(')', Relation.takes, '*')
                .setEntry(')', Relation.takes, ')')

                .setEntry('n', Relation.takes, '+')
                .setEntry('n', Relation.takes, '*')
                .setEntry('n', Relation.takes, ')');

        State q_0 = new State("q_0");
        State q_1 = new State("q_1");
        State q_2 = new State("q_2");
        State q_3 = new State("q_3");

        Transitions trans = new Transitions();
        trans.addAll(
                new Push_Transition(q_0, 'n', q_1),

                new Push_Transition(q_1, '+', q_0),
                new Push_Transition(q_1, '*', q_0),

                new Push_Transition(q_0, '(', q_2),
                new Push_Transition(q_2, '(',q_2),

                new Push_Transition(q_2, 'n', q_3),

                new Push_Transition(q_3, '*', q_2),
                new Push_Transition(q_3, '+', q_2),

                new Shift_Transition(q_3, ')', q_3),

                new Pop_Transition(q_1, q_0, q_1),
                new Pop_Transition(q_1, q_1, q_1),

                new Pop_Transition(q_3, q_0, q_3),
                new Pop_Transition(q_3, q_1, q_3),
                new Pop_Transition(q_3, q_2, q_3),
                new Pop_Transition(q_3, q_3, q_3)
        );

        OP_Automat opa = new OP_Automat.Builder()
                .withTerminalSet('+', '*', '(', ')', 'n')
                .basedOnMatrix(opm)
                .hasStates(q_0, q_1, q_2, q_3)
                .startsAtState(q_0)
                .acceptsAtStates(q_1, q_3)
                .withTransitions(trans)
                .build();

        opa.printAutomat();

        new Computation(opa, "(n+n*(n+n))*(n+n)").compute();
        new Computation(opa, "n*(n+n*n)").compute();

        OPA_WriterReader rw = new OPA_WriterReader();

        rw.saveOPA(opa, "src/main/resources/opa_1.txt");

        OP_Automat opa1 = rw.readOPA("src/main/resources/opa_1.txt");


    }
}
