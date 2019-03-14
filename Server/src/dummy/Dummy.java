/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dummy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Florin
 */
public class Dummy extends Application {
       private static  String IP = "192.168.56.1";
    private static  int PORT = 3055;
    

    public void setIP (String IP){
        this.IP = IP ;
    }
    public void setPort(int PORT){
        this.PORT = PORT ;
    }
    public void createSocket (){
         try {
            Socket s = new Socket(IP,PORT);
           
            PrintWriter writer = new PrintWriter(s.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            while(true) {
                String sel = reader.readLine();
                switch(sel) {
                    case "PING":	writer.println(s.getRemoteSocketAddress());
                        writer.flush();
                        break;
                    case "CLOSE":	s.close();
                        return;
                    case "PCNAME":	InetAddress addr = InetAddress.getLocalHost();
                        writer.println(addr.getHostName());
                        writer.flush();
                        break;
                    case "OS":		writer.println(System.getProperty("os.name"));
                        writer.flush();
                        break;
                    case "IP":		writer.println(s.getRemoteSocketAddress());
                        writer.flush();
                        break;
                    default:		break;
                }
            }
        }
        catch (Exception e) {}
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
       
        
       
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         
   
        launch(args);
    }
}
    

