/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejemplos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author N550J
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label label;    
    @FXML private Chart mibarchart ;
    @FXML private TextField txtcliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML private void generarReporte(ActionEvent E){
        //System.out.println(""+txtcliente.getText().toString());
        String hola=txtcliente.getText().toString();
        System.out.println(""+hola);
    }
    
    public void salir(){
        System.exit(0);
    }
}
