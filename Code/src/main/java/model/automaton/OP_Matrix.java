package model.automaton;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OP_Matrix {

    List<Character> symbols;
    Character endsymbol;
    private int size;
    private Relation[][] matrix;

    /**
     * The constructor creates a new matrix based on the number of symbols. Adds an endsymbol to the terminal symbols
     * @param terminals
     */

    public OP_Matrix(Set<Character> terminals) {
        this.symbols = new ArrayList(terminals);
        this.endsymbol = '#';
        this.symbols.add(endsymbol);

        this.size = symbols.size();

        this.matrix = new Relation[size][size];


        for(Character c: symbols){
            if(c.equals(this.endsymbol)) this.setEntry(this.endsymbol, Relation.equal, this.endsymbol);
            else{
                this.setEntry(this.endsymbol, Relation.yield, c);
                this.setEntry(c, Relation.takes, this.endsymbol);
            }
        }
    }

    /**
     * Returns the Precedence Relation of two terminal symbols: a [precedence relation] b
     * @param a
     * @param b
     * @return
     */
    public Relation getEntry(char a, char b){
        if(!symbols.contains(a)){
            System.out.println("Character "+ a + " not part of Terminal List");
            return null;
        }
        if(!symbols.contains(b)){
            System.out.println("Character "+ b + " not part of Terminal List");
            return null;
        }

        int a_i = 0;
        int b_i = 0;

        for(int i = 0;i < this.size; i++){
            if(this.symbols.get(i).equals(a)) a_i = i;
            if(this.symbols.get(i).equals(b)) b_i = i;
        }

        return this.matrix[a_i][b_i];
    }

    /**
     * Set the precedence relation for two terminal symbols: a [precedence relation] b
     * @param a
     * @param rel
     * @param b
     */
    public void setEntry(char a, Relation rel, char b){
        if(!symbols.contains(a)){
            System.out.println("Character "+ a + " not part of Terminal List");
            return;
        }
        if(!symbols.contains(b)){
            System.out.println("Character "+ b + " not part of Terminal List");
            return;
        }

        int a_i = 0;
        int b_i = 0;

        for(int i = 0;i < this.size; i++){
            if(this.symbols.get(i).equals(a)) a_i = i;
            if(this.symbols.get(i).equals(b)) b_i = i;
        }
        this.matrix[a_i][b_i] = rel;
    }

    /**
     * Checks for Inconsistencies regarding the Matrix Size and the numbers of Terminals
     * @return true, if the size is fitting. false, if not
     */
    public boolean checkMatrixSize(){
        if (this.matrix[0].length == symbols.size() && this.matrix.length == symbols.size()){
            return true;
        }
        else {
            System.out.println("OP Matrix length doesnt fit the number of symbols");
            return false;
        }
    }


    /**
     * Print Method for Testing purposes
     */
    public void printMatrix(){
        System.out.println("Operator precedence matrix:");
        for(Character a: symbols){
            for(Character b: symbols){
                if(this.getEntry(a, b) == null){
                    System.out.print("  -   ");
                }
                else System.out.print(this.getEntry(a, b) + " ");
            }
            System.out.println("");
        }
    }

    /*
    ############### Simple Setter and Getter Functions #####################
     */

    public List<Character> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Character> symbols) {
        this.symbols = symbols;
    }

    public Relation[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Relation[][] matrix) {
        this.matrix = matrix;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
