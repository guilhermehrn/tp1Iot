package rfidLeitor;

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
    
    private String ipLeitor = "150.164.10.41";
    private int portaLeitor = 23;
    private String usernameLeitor ="alien";
    private String passWordLeitor = "password";
    private String caminholog = "/";
    
    

    /**
     * @return the ipLeitor
     */
    public String getIpLeitor() {
        return ipLeitor;
    }

    /**
     * @param ipLeitor the ipLeitor to set
     */
    public void setIpLeitor(String ipLeitor) {
        this.ipLeitor = ipLeitor;
    }

    /**
     * @return the portaLeitor
     */
    public int getPortaLeitor() {
        return portaLeitor;
    }

    /**
     * @param portaLeitor the portaLeitor to set
     */
    public void setPortaLeitor(int portaLeitor) {
        this.portaLeitor = portaLeitor;
    }

    /**
     * @return the usernameLeitor
     */
    public String getUsernameLeitor() {
        return usernameLeitor;
    }

    /**
     * @param usernameLeitor the usernameLeitor to set
     */
    public void setUsernameLeitor(String usernameLeitor) {
        this.usernameLeitor = usernameLeitor;
    }

    /**
     * @return the passWordLeitor
     */
    public String getPassWordLeitor() {
        return passWordLeitor;
    }

    /**
     * @param passWordLeitor the passWordLeitor to set
     */
    public void setPassWordLeitor(String passWordLeitor) {
        this.passWordLeitor = passWordLeitor;
    }

    /**
     * @return the caminholog
     */
    public String getCaminholog() {
        return caminholog;
    }

    /**
     * @param caminholog the caminholog to set
     */
    public void setCaminholog(String caminholog) {
        this.caminholog = caminholog;
    }

       
    /**
     * Conecta ao leitor de forma ativa e depois tertorna uma lista de tags lida
     * @return retorna um lista do tipo tag
     * @throws AlienReaderException 
     */
    public void lerAtivamente() throws AlienReaderException{
        
        AlienClass1Reader reader = new AlienClass1Reader();
        //reader.setConnection("COM1");

        // To connect to a networked reader instead, use the following:
        reader.setConnection(this.getIpLeitor(), this.getPortaLeitor());
        reader.setUsername(this.getUsernameLeitor());
        reader.setPassword(this.getPassWordLeitor());

        // Open a connection to the reader
        reader.open();

        // Ask the reader to read tags and print them
        Tag tagList[] = reader.getTagList();
        
       
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
        
       
        // Close the connection
        reader.close();
        
       // return tagList;
        
    }
    
    
    
    public void lerPassivamente(){
        //TODO
    }
    
    public Tp1IotReader()  {
            
    }

    
    public void gravarLog(){
        //TODO
    }
    
    public void pararLeitoraPassiva(){
        //TODO
    }

//    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        try {
//            //Tp1Inew Tp1IotReader();
//            
//            Tp1IotReader read = new Tp1IotReader();
//            read.lerAtivamente();
//            
//        } catch (AlienReaderException e) {
//            System.out.println("Error: " + e.toString());
//        }
//
//    }
}
