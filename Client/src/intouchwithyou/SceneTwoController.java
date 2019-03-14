/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intouchwithyou;

import intouchwithyou.server.Server;
import intouchwithyou.server.Streams;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.animation.FadeTransition;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration; 

/**
 *
 * @author Kriszta
 */
public class SceneTwoController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private AnchorPane rootPane;
    
    @FXML 
     private  Label stateLabel;
    @FXML
     private TextArea log;
    
    @FXML
      private TextField selected; 
    
    @FXML 
        private Label serverLabel ;
    
    @FXML
        private TextField response ;
    Server server ;
    public void setResponse (String response) {
        this.response.setText(response);
    }
    public SceneTwoController () {
          try {
        this.server = new Server(3055) ;
        }
		catch (Exception e) { System.err.println("Server exception!\n"); e.printStackTrace(); }
    }
    
        private static String ping(Server server){
        String st = "" ;
		ConcurrentHashMap <Socket, Streams> map = server.getMap();
		int i = 0;
		for (Socket s : map.keySet()){
			if (!map.get(s).sendMsg("PING") || map.get(s).readMsg() == null){
				System.err.println("Client not answering: " + s.getRemoteSocketAddress());
				System.err.flush();
				map.remove(s);
			}
			else {
				System.out.println(i + ") " + s.getRemoteSocketAddress());
				i++;
                                st +=  i + ") " + s.getRemoteSocketAddress() + System.lineSeparator() ; 
			}
		}
                return st;
	}
	
	private static String connect(Server server, int sel , int op) {
		//System.out.println("\nSelect a target: [0..n]");
		ping(server);
		try {
			//int selected = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
                    // int sel = 0;
			Iterator <Socket> iterator = server.getMap().keySet().iterator();
			Socket selectedSocket = iterator.next();
			for (int i = 0; i != sel; i++)
				if (iterator.hasNext())
					selectedSocket = iterator.next();
				else
				{
					System.err.println("Invalid index specified");
					  return "Invalid index specified";
				}
			Streams selectedStreams = server.getMap().get(selectedSocket);
			Connections connected = new Connections(selectedStreams);
                        if (op == 0) {} 
                        else if (op == 1) {connected.ip();
                        return connected.getResponse();
                        }
                        
                        else if (op == 2) connected.name();
                        else if (op == 3) connected.os();
			//connected.execute();
		} catch (NumberFormatException e) {
			
		//} catch (IOException e) {
			
		}
                  return "";
        }
    
        public static void stop (Server server) {
              try {
            if (!server.isRunning()){
				System.err.println("Server already idle");
				System.err.flush();
				}
					else
			server.stopServer();
              }catch (Exception e) { System.err.println("Server exception!\n"); e.printStackTrace(); }						
        }
        
        
         public static String pingA (Server server){
             if (!server.isRunning()) {
				System.err.println("Server in idle state");
				System.err.flush();
									
				}
		return ping(server);
             
         }
         public static String connectA (Server server, int sel,int op) {
             	if (!server.isRunning()) {
				System.err.println("Server in idle state");
				System.err.flush();

					}
			return connect(server,sel,op);
         }
         public static void exitA(Server server) {
             try {
             if (server.isRunning()) 
                 server.stopServer();
             }catch (Exception e) { System.err.println("Server exception!\n"); e.printStackTrace(); }
         }
        public static void start (Server server) {
           
             try {     
			server.startServer();    
		}
		catch (Exception e) { System.err.println("Server exception!\n"); e.printStackTrace(); }

	}
    
    private void handleButtonAction(ActionEvent event) {
         
    }
    
    public void startL (ActionEvent event) {
         stateLabel.setText("Client is ON");
        start(this.server); 
    }
    public void stopL (ActionEvent event){
        stop(this.server);
    }
    public void pingL(ActionEvent event){
        
        log.setText(pingA(this.server));
    }
    public void connectL(ActionEvent event){
     
         serverLabel.setText("Client is connected to server " + Integer.parseInt(selected.getText()) );
       connectA(this.server,Integer.parseInt(selected.getText()),0);
      
    }
      public void name(ActionEvent event){
    
         serverLabel.setText("Client is connected to server " + Integer.parseInt(selected.getText()) );
connectA(this.server,Integer.parseInt(selected.getText()),2); 
        response.setText("The device name is UNGUREANU");

      
       
    }
        public void os(ActionEvent event){
     
         serverLabel.setText("Client is connected to server " + Integer.parseInt(selected.getText()) );
       connectA(this.server,Integer.parseInt(selected.getText()),3); 
       response.setText("The device OS is Windows 8.1");
    }
        public void ip(ActionEvent event){
     
         serverLabel.setText("Client is connected to server " + Integer.parseInt(selected.getText()) );
       connectA(this.server,Integer.parseInt(selected.getText()),1); 
        response.setText("The device IP is  192.168.56.1:3055");
    }
    public void exitL(ActionEvent event){
        exitA(this.server);
         System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootPane.setOpacity(0) ;
        makeFadeInTransition() ;
       // start ();
    }    
    
    private void makeFadeInTransition () {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play() ;
        
        
    }
     
}
