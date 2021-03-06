package rfidLeitor;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;

import com.alien.enterpriseRFID.notify.Message;
import com.alien.enterpriseRFID.notify.MessageListener;
import com.alien.enterpriseRFID.notify.MessageListenerService;

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
public class Tp1IotReader implements MessageListener {
    
    private String ipLeitor = "150.164.10.41";
    private int portaLeitor = 23;
    private String usernameLeitor ="alien";
    private String passWordLeitor = "password";
    private String caminholog = "/tmp";
    
    private int readTime = 5; // segundos
    private Item tags[];
    public int numTags;
    private String modo;
    private String efeito;
    private String distancia;
    private float timeout;
    private float autoPort;
    
    AlienClass1Reader reader;
    
    public Tp1IotReader() throws AlienReaderException {
    	
    	reader = new AlienClass1Reader();
    	
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
    
    public void setPortaAuto(float port) {
    	this.autoPort = port;
    }
    
    public float getPortaAuto() {
    	return this.autoPort;
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
    	// Set connection
    	// To connect to a networked reader instead, use the following:
        reader.setConnection(this.getIpLeitor(), this.getPortaLeitor());
        reader.setUsername(this.getUsernameLeitor());
        reader.setPassword(this.getPassWordLeitor());
    	
        reader.open();

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
		
		int count = 1;
		while (false || (System.currentTimeMillis() - startTime) < (readTime * 1000)) {
			// Read
			commandOut = reader.doReaderCommand("t");
			str = reader.getReaderReply();
			
			// Get ids
			String items[] = str.split("\n");
			for(String tag : items) {
				if(!tag.equals("(No Tags)")) {
					String fields[] = tag.split(", ");
					String id = fields[0].substring(4);
					String antenna = fields[5].substring(4,5);
					
					for(int i = 0; i < numTags; i++) {
						if(tags[i].id.equals(id) && tags[i].antenna.equals(antenna)) {
							tags[i].reads++;
						}
					}
				}
			}
			
			count++;
		}
		
		// Calc Statistics
		for(Item tag : tags) {
			
			tag.successRate = tag.reads/count;
			tag.reads = tag.reads/readTime;
			tag.distanceReal = distancia;
			
		} 
		
		// Set string format back
		reader.setTagListFormat(AlienClass1Reader.XML_FORMAT);
        
        this.gravarLog();
        
        reader.close();
    }
    
    public Item[] getResults() {
    	return tags;
    }
    
    public int numTags() {
    	return numTags;
    }
    
    public void lerAuto() throws IOException, AlienReaderException, InterruptedException {
    	// Set connection
    	// To connect to a networked reader instead, use the following:
    	String myIp = "150.164.10.9";
    	
    	MessageListenerService service = new MessageListenerService((int) this.getPortaAuto());
    	  service.setMessageListener(this);
    	  service.startService();
    	  System.out.println("Message Listener has Started");

    	  // Instantiate a new reader object, and open a connection to it on COM1
    	  AlienClass1Reader reader = new AlienClass1Reader();
    	  reader.setConnection(this.getIpLeitor(), this.getPortaLeitor());
    	  reader.setUsername(this.getUsernameLeitor());
    	  reader.setPassword(this.getPassWordLeitor());
    	  
    	  reader.open();
    	  System.out.println("Configuring Reader");

    	  // Set up Notification.
    	  // Use this host's IPAddress, and the port number that the service is listening on.
    	  // getHostAddress() may find a wrong (wireless) Ethernet interface, so you may
    	  // need to substitute your computers IP address manually.
    	  reader.setNotifyAddress(myIp, service.getListenerPort());
    	  reader.setNotifyFormat(AlienClass1Reader.XML_FORMAT); // Make sure service can decode it.
    	  reader.setNotifyTrigger("False"); // Notify whether there's a tag or not
    	  reader.setNotifyMode(AlienClass1Reader.ON);

    	  // Set up AutoMode
    	  reader.autoModeReset();
    	  reader.setAutoStopTimer((int) this.getTimeout()*1000); // Read for 1 second
    	  reader.setAutoMode(AlienClass1Reader.ON);

    	  // Close the connection and spin while messages arrive
    	  reader.close();
    	  long runTime = (long) this.getTimeout()*1000; // milliseconds
    	  long startTime = System.currentTimeMillis();
    	  do {
    	    Thread.sleep(1000);
    	  } while(service.isRunning() && (System.currentTimeMillis()-startTime) < runTime);
    	  
    	  // Reconnect to the reader and turn off AutoMode and TagStreamMode.
    	  System.out.println("\nResetting Reader");
    	  reader.open();
    	  reader.autoModeReset();
    	  reader.setNotifyMode(AlienClass1Reader.OFF);
    	  reader.close();
    	  
    	  service.stopService();
    	  
    	  for(int i = 0; i < numTags; i++) {
    		  tags[i].reads = tags[i].count / this.getTimeout();
    		  tags[i].distanceReal = distancia;
    	  }
    	  
    	  this.gravarLog();
    }
    
    public void gravarLog()
    	throws IOException {
    		
    		BufferedWriter writer = new BufferedWriter(new FileWriter(caminholog + "rfid.log", true));
    		String timestamp = new Timestamp(System.currentTimeMillis()).toString();
    		
    		String output = "";
    		
			for(Item tag : tags) {
				
				output = output + 
						 timestamp 		+ ';' +
						 tag.id 		+ ';' + 
						 tag.last 		+ ';' + 
						 tag.rssi 		+ ';' + 
						 tag.speed 		+ ';' + 
						 tag.reads 		+ ';' +
						 tag.successRate+ ';' +
						 tag.antenna 	+ ';' +
						 modo 			+ ';' +
						 efeito 		+ ';' +
						 distancia 		+ '\n';
            }
			
    		writer.append(output);
    		writer.close();
	}
    
    public void pararLeitoraPassiva(){
        //TODO
    }

	@Override
	public void messageReceived(Message msg) {
	  if (msg.getTagCount() != 0 && numTags == 0) {
		  tags = new Item[msg.getTagCount()];
		  numTags = msg.getTagCount();
	
		  for(int i = 0; i < msg.getTagCount(); i++) {
			  Tag tag = msg.getTag(i);
			  tags[i] = new Item(tag.getTagID(), tag.getRenewCount());
			  tags[i].antenna = Integer.toString(tag.getAntenna());
		  }
	  } else if (msg.getTagCount() != 0 && numTags > 0){
	    for (int i = 0; i < msg.getTagCount(); i++) {
	      Tag tag = msg.getTag(i);
	      
	      for(int j = 0; j < tags.length; j++) {
	    	  if(tag.getTagID().equals(tags[i].getId()) && tags[i].antenna.equals(Integer.toString(tag.getAntenna()))) {
	    		  tags[i].addToCount(tag.getRenewCount());
	    	  }
	      }
	    }
	  }
	}
}

