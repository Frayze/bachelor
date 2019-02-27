package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class OP_MatrixTest {

    OP_Matrix opm1;
    OP_Matrix opm2;

    @Before
    public void setUp() throws Exception {
       Set ar = new HashSet();
       ar.add('n'); ar.add('+'); ar.add('*'); ar.add('n'); ar.add('('); ar.add(')');

       opm1 = new OP_Matrix(ar);

       opm2 = new OP_Matrix(new HashSet<Character>());



    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getEntry() {
        assertEquals(Relation.yield, opm1.getEntry('#', 'n'));
        assertEquals(Relation.takes, opm1.getEntry('+', '#'));
        assertEquals(Relation.equal, opm1.getEntry('#', '#'));
    }

    @Test
    public void setEntry() {
    }

    @Test
    public void checkMatrixSize() {
    }

    @Test
    public void getSize(){
        assertEquals(6, opm1.getSize());
        assertEquals(1, opm2.getSize());
    }
}