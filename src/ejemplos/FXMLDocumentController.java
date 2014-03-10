/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejemplos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author N550J
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label label;    
    @FXML private PieChart mibarchar ;
    @FXML private TextField txtcliente;
   // @FXML private LineChart graph;
    @FXML private NumberAxis xAxis, yAxis;
    @FXML private Button blinechart;
     @FXML private LineChart<Double, Double> graph;
     @FXML private BubbleChart<Double, Double> buble;
    @FXML private NumberAxis x;
    @FXML private NumberAxis y;
//    @FXML private LineChart<String, Number> graph;      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML private void generarReporte(ActionEvent E){        
        ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList(
            new PieChart.Data("Executed", 60),
            new PieChart.Data("Passed", 25),
            new PieChart.Data("Fails", 15),
            new PieChart.Data("Coronamos", 15)
        ); 
    mibarchar.setData(pieChartData); 
}
    @FXML private void reporteLine(ActionEvent E){
    ObservableList<XYChart.Series<Double, Double>> lineChartData = FXCollections.observableArrayList();  
        LineChart.Series<Double, Double> series = new LineChart.Series<Double, Double>(); 
       for (double i = 0; i<100; i=i+0.1){
            series.getData().add(new XYChart.Data<Double, Double>(i, (i*2)-6));
        } 
        lineChartData.add(series);
        graph.setCreateSymbols(true); 
        graph.setData(lineChartData);
        graph.createSymbolsProperty();
    }
    
    @FXML private void reportePrecio(ActionEvent e){
        final NumberAxis xAxis = new NumberAxis(1, 53, 4);
        final NumberAxis yAxis = new NumberAxis(0, 80, 10);
        
     ObservableList<XYChart.Series<Double, Double>> lineChartData = FXCollections.observableArrayList();
       BubbleChart.Series<Double,Double>series=new BubbleChart.Series<Double, Double>();
//        for (double i = 0; i < 100; i++) {
            series.getData().add(new XYChart.Data(8, 15, 2));
            series.getData().add(new XYChart.Data(13, 23, 1));
            series.getData().add(new XYChart.Data(15, 45, 3));
            series.getData().add(new XYChart.Data(24, 30, 4.5));
            series.getData().add(new XYChart.Data(38, 78, 1));
            series.getData().add(new XYChart.Data(40, 41, 7.5));
            series.getData().add(new XYChart.Data(45, 57, 2));
            series.getData().add(new XYChart.Data(47, 23, 3.8));
//        } 
         lineChartData.add(series);
         buble.setData(lineChartData);          
    }
    
    public void salir(){
        System.exit(0);
    }
}
