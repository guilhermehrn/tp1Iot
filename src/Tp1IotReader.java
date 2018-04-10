/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.alien.enterpriseRFID.reader.*;
import com.alien.enterpriseRFID.tags.*;

/**
 *
 * @author guilhermehenrique
 */
public class Tp1IotReader {
    
    public String ipLeitor = "150.164.10.41";
    public int portaLeitor = 23;
    public String usernameLeitor ="alien";
    public String passWordLeitor = "password";
    
   
    /**
     * Conecta ao leitor de forma ativa e depois tertorna uma lista de tags lida
     * @return retorna um lista do tipo tag
     * @throws AlienReaderException 
     */
    public Tag[] lerAtivamente() throws AlienReaderException{
        
        AlienClass1Reader reader = new AlienClass1Reader();
        //reader.setConnection("COM1");

        // To connect to a networked reader instead, use the following:
        reader.setConnection(this.ipLeitor, this.portaLeitor);
        reader.setUsername(this.usernameLeitor);
        reader.setPassword(this.passWordLeitor);

        // Open a connection to the reader
        reader.open();

        // Ask the reader to read tags and print them
        Tag tagList[] = reader.getTagList();
        
        /*
        if (tagList == null) {
            System.out.println("No Tags Found");
        } else {
            System.out.println("Tag(s) found:");
            for (int i = 0; i < tagList.length; i++) {
                Tag tag = tagList[i];
                System.out.println("ID:" + tag.getTagID()
                        + ", Discovered:" + tag.getDiscoverTime()
                        + ", Last Seen:" + tag.getRenewTime()
                        + ", Antenna:" + tag.getAntenna()
                        + ", Reads:" + tag.getRenewCount()
                );
            }
        }

        */
        // Close the connection
        reader.close();
        
        return tagList;
        
    }
    
    
    public void lerPassivamente(){
        
    }
    
    public Tp1IotReader()  {
        
            
        
    }


    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Tp1Inew Tp1IotReader();
            
            Tp1IotReader read = new Tp1IotReader();
            read.lerAtivamente();
            
        } catch (AlienReaderException e) {
            System.out.println("Error: " + e.toString());
        }

    }

}
