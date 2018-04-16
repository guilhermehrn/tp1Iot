package rfidLeitor;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;

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
    private String caminholog = "/tmp";
    
    private int readTime = 5; // segundos
    private Item tags[];
    private int numTags;
    private String modo;
    private String efeito;
    private String distancia;
    private float timeout;
    
    AlienClass1Reader reader;
    
    public Tp1IotReader() throws AlienReaderException {
    	
    	reader = new AlienClass1Reader();
    	
    	// To connect to a networked reader instead, use the following:
        reader.setConnection(this.getIpLeitor(), this.getPortaLeitor());
        reader.setUsername(this.getUsernameLeitor());
        reader.setPassword(this.getPassWordLeitor());
    	
        reader.open();
    }
    
    public float getTimeout() {
		return timeout;
	}

	public void setTimeout(float timeout) {
		this.timeout = timeout;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

    public void setModo(String __modo){
    	
    	this.modo = __modo;
    }
    
    public String getModo(){
    	
    	return modo;
    }
    
    public void setEfeito(String __efeito){
    	
    	this.efeito = __efeito;
    }
    
    public String getEfeito() {
    	
    	return efeito;
    }
    
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
    public void lerAtivamente() throws AlienReaderException, IOException {

        // Set string format
 		String customFormatStr = "Tag:${TAGID}, Last:${MSEC2}, RSSI=${RSSI}, Speed:${SPEED}, Reads:${COUNT}, Ant:%a";
 		reader.setTagListFormat(AlienClass1Reader.CUSTOM_FORMAT);
 		reader.setTagListCustomFormat(customFormatStr);
 		reader.setNotifyTrigger("OFF");
 		
 		// Read tags
		String commandOut = reader.doReaderCommand("t");
		String str = reader.getReaderReply();
		
		// Get ids and init values
		String lines[] = str.split("\n");
		numTags = lines.length;
		tags = new Item[numTags];
		
		for(int i = 0; i < numTags; i++) {
			tags[i] = new Item(lines[i]);
		}
		
		// Read tags for X seconds
		long startTime = System.currentTimeMillis();
		
		while (false || (System.currentTimeMillis() - startTime) < (readTime * 1000)) {
			// Read
			commandOut = reader.doReaderCommand("t");
			str = reader.getReaderReply();
			
			// Get ids
			String items[] = str.split("\n");
			for(String tag : items) {
				String fields[] = tag.split(", ");
				String id = fields[0].substring(4);
				
				for(int i = 0; i < numTags; i++) {
					if(tags[i].id.equals(id)) {
						tags[i].reads++;
					}
				}
			}
		}
		
		// Calc Statistics
//		for(Item tag : tags) {
//			
//			tag.reads = tag.reads/readTime;
//			
//			tag.distanceReal = Float.parseFloat(this.distancia);
//			
//		} 
		// Results
		/*for(int i = 0; i < numTags; i++) {
			System.out.print(tags[i].id + ", " + tags[i].rssi + ", " + tags[i].speed + ", " + tags[i].reads + "\n");
		}*/
		
		// Set string format back
		reader.setTagListFormat(AlienClass1Reader.XML_FORMAT);

		// Close the connection
//        reader.close();
        
        this.gravarLog();
    }
    
    public Item[] getResults() {
    	return tags;
    }
    
    public int numTags() {
    	return numTags;
    }
    
    public void lerPassivamente(long __timeout) throws AlienReaderException, IOException {

//    	
//    	// To connect to a networked reader instead, use the following:
//        reader.setConnection(this.getIpLeitor(), this.getPortaLeitor());
//        reader.setUsername(this.getUsernameLeitor());
//        reader.setPassword(this.getPassWordLeitor());
//
//        // Open a connection to the reader
//        reader.open();
//        
//        // Set string format
// 		String customFormatStr = "Tag:${TAGID}, Last:${MSEC2}, RSSI=${RSSI}, Speed:${SPEED}, Reads:${COUNT}, Ant:%a";
// 		reader.setTagListFormat(AlienClass1Reader.CUSTOM_FORMAT);
// 		reader.setTagListCustomFormat(customFormatStr);
// 		reader.setNotifyTrigger("OFF");
// 		
// 		long startTimeUser = System.currentTimeMillis();
// 		
// 		while ((System.currentTimeMillis() - startTimeUser) < (__timeout * 1000)){
// 			
// 	 		// Read tags
// 			String commandOut = reader.doReaderCommand("t");
// 			String str = reader.getReaderReply();
// 			
// 			// Get ids and init values
// 			String lines[] = str.split("\n");
// 			numTags = lines.length;
// 			tags = new Item[numTags];
// 			
// 			for(int i = 0; i < numTags; i++) {
// 				tags[i] = new Item(lines[i]);
// 			}
// 			
// 			// Read tags for X seconds
// 			long startTime = System.currentTimeMillis();
// 			
// 			while ((System.currentTimeMillis() - startTime) < (readTime * 1000)) {
// 				// Read
// 				commandOut = reader.doReaderCommand("t");
// 				str = reader.getReaderReply();
// 				
// 				// Get ids
// 				String items[] = str.split("\n");
// 				for(String tag : items) {
// 					String fields[] = tag.split(", ");
// 					String id = fields[0].substring(4);
// 					
// 					for(int i = 0; i < numTags; i++) {
// 						if(tags[i].id.equals(id)) {
// 							tags[i].reads++;
// 						}
// 					}
// 				}
// 			}
// 		}
//		
//		// Calc Statistics
//		for(Item tag : tags) {
//			
//			tag.reads = tag.reads/readTime;
//			
////			tag.distanceReal = Float.parseFloat(this.distancia);
//			
//		}
//		
//		// Set string format back
//		reader.setTagListFormat(AlienClass1Reader.XML_FORMAT);
//
//		// Close the connection
//        reader.close();
//        
//        this.gravarLog();
//    	
    	
    }

    
    public void gravarLog()
    	throws IOException {
    		
    		BufferedWriter writer = new BufferedWriter(new FileWriter(caminholog + "rfid.log", true));
    		String timestamp = new Timestamp(System.currentTimeMillis()).toString();
    		
    		String output = "";
    		
			for(Item tag : tags) {
				
				output = output + 
						 timestamp + ';' +
						 tag.id + ';' + 
						 tag.last + ';' + 
						 tag.rssi + ';' + 
						 tag.speed + ';' + 
						 tag.reads + ';' + 
						 tag.reads + ';' + 
						 modo + ';' +
						 efeito + ';' +
						 distancia + '\n';
            }
    		
    		writer.append(output);
    		writer.close();
	}
    
    public void pararLeitoraPassiva(){
        //TODO
    }
}

