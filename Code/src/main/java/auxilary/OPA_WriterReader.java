package auxilary;

import model.automaton.OP_Automat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class OPA_WriterReader {

    public void saveOPA(OP_Automat opa, String dest){
        try {
            File destFile = new File(dest);
            destFile.createNewFile();
            FileOutputStream f = new FileOutputStream(destFile);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(opa);
            o.close();

            System.out.println("Save successfull");

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error Initializing Stream");
        }


    }

    public OP_Automat readOPA(String orig){
        OP_Automat opa = null;
        try{
            FileInputStream fi = new FileInputStream(new File(orig));
            ObjectInputStream oi = new ObjectInputStream(fi);
             opa = (OP_Automat) oi.readObject();

            oi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(opa == null) System.out.println("Error Loading OPA");
        else System.out.println("Loading OPA Successfull");
        return opa;
    }


}
