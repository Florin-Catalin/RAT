/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intouchwithyou;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration; 
import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene; 
import javafx.scene.control.TextField;
import javafx.stage.Stage; 

/**
 *
 * @author Kriszta
 */
public class SceneOneController implements Initializable {
    
  

  
    @FXML
    private AnchorPane rootPane1;
    @FXML
    private AnchorPane rootPane2;
    
      @FXML
  private TextField user ;
      @FXML
   private TextField pass ;
      
      @FXML
      private Label question ;
      
    @FXML
    private void handleButtonAction(ActionEvent event) {
      if (user.getText().equals("user")&& pass.getText().equals("pass"))
        makeFadeOut();
      else
          question.setText("Login Failed.Forgot Password ?");
    }
    
    private void handleClose(ActionEvent event) {
       
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private void makeFadeOut(){
        FadeTransition fadeTransition = new FadeTransition ();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        
       fadeTransition.setOnFinished((ActionEvent event) -> {
           loadNextScene();
       });
        fadeTransition.play();
    
        fadeTransition.setNode(rootPane2);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    
    }
    private void loadNextScene(){
        try{
        Parent secondView;
        secondView = (AnchorPane)FXMLLoader.load(getClass().getResource("SceneTwo.fxml"));
        Scene newScene = new Scene(secondView);
        
        Stage curStage = (Stage)rootPane1.getScene().getWindow();
        curStage.setScene(newScene);
        
      
                   
        }catch (Exception ex) {
            Logger.getLogger(SceneOneController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }
 
}
