/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejemplos;

import accesoDatos.Conexion;
import accesoDatos.Consultas;
import eu.schudt.javafx.controls.calendar.DatePicker;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author N550J
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label label;    
    @FXML private PieChart mibarchar ;
    @FXML private TextField txtcliente;
    //@FXML private LineChart graph;
    @FXML private NumberAxis xAxis, yAxis;
    @FXML private Button blinechart;
    @FXML private LineChart<Double, Double> graph;
    @FXML private BubbleChart<Double, Double> buble;
    @FXML private NumberAxis x;
    @FXML private NumberAxis y;
    @FXML private ComboBox<String> combo;
    @FXML private GridPane gridpane;
    @FXML private DatePicker dp;
    private double cos;
  private double sin;
   static final Duration ANIMATION_DURATION = new Duration(500);
  static final double ANIMATION_DISTANCE = 0.15;
    //@FXML private LineChart<String, Number> graph;
    
    //Consultas ComboBox
    @FXML private ComboBox tipo_filtro;
    @FXML private ComboBox combo_empresa;
    @FXML private ComboBox combo_medida;
    @FXML private ComboBox combo_franja;
    @FXML private ComboBox combo_tipo;
    @FXML private Label caption ;
    String drilldownCss="";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dp = new DatePicker(Locale.ENGLISH);
        drilldownCss = FXMLDocumentController.class.getResource("/estilos/DrilldownChart.css").toExternalForm();
        Conexion con = new Conexion();         
        assert mibarchar != null : "fx:id=\"mibarchar\" was not injected: check your FXML file 'FXMLDocumetn.fxml'.";
        assert combo_tipo != null : "fx:id=\"combo_tipo\" was not injected: check your FXML file 'FXMLDocumetn.fxml'.";
        ObservableList<String> optionsTipo = FXCollections.observableArrayList("consolidado", "promedio");
        combo_tipo.setItems(optionsTipo);
        combo_tipo.getSelectionModel().selectLast();
        
        assert tipo_filtro != null : "fx:id=\"tipo_filtro\" was not injected: check your FXML file 'FXMLDocumetn.fxml'.";
        ObservableList<String> options = FXCollections.observableArrayList("ciudad", "cliente", "empresa");
        tipo_filtro.setItems(options);
        tipo_filtro.getSelectionModel().selectLast();
        
        assert combo != null : "fx:id=\"combo\" was not injected: check your FXML file 'FXMLDocumetn.fxml'.";      
        ObservableList<String> datosComboValues=con.LlenarCommbo("select ciudad from ciudad order by id_ciudad");
        datosComboValues.add("Todos");
        combo.getItems().clear();
        combo.setItems(datosComboValues);
        combo.getSelectionModel().selectLast();        
        
        assert combo_empresa != null : "fx:id=\"combo_empresa\" was not injected: check your FXML file 'FXMLDocumetn.fxml'.";      
        ObservableList<String> datosComboEmpresa=con.LlenarCommbo("select empresa from empresa order by id_empresa");
        datosComboEmpresa.add("Todos");
        combo_empresa.getItems().clear();
        combo_empresa.setItems(datosComboEmpresa);
        combo_empresa.getSelectionModel().selectLast();
        
        assert combo_medida != null : "fx:id=\"combo_medida\" was not injected: check your FXML file 'FXMLDocumetn.fxml'.";      
        ObservableList<String> datosComboMedida=con.LlenarCommbo("select medida from medida order by id_medida");
        datosComboMedida.add("Todos");
        combo_medida.getItems().clear();
        combo_medida.setItems(datosComboMedida);
        combo_medida.getSelectionModel().selectLast();
        
        assert combo_franja != null : "fx:id=\"combo_franja\" was not injected: check your FXML file 'FXMLDocumetn.fxml'.";      
        ObservableList<String> datosComboFranja=con.LlenarCommbo("select distinct (franja_horaria) from tiempo");
        datosComboFranja.add("Todos");
        combo_franja.getItems().clear();
        combo_franja.setItems(datosComboFranja);
        combo_franja.getSelectionModel().selectLast();
    }  
    
    @FXML private void generarReporte(ActionEvent E){        
        
        Conexion con = new Conexion();
        Consultas consul = new Consultas();
        
        ArrayList<String> tablaSubConWhere = new ArrayList();
        ArrayList<String> id_tablaSubConWhere = new ArrayList();
        ArrayList<String> valoresWhere = new ArrayList();
        
        if(combo.getValue().equals("Todos"))
        {
        }
        else
        {
            tablaSubConWhere.add("ciudad");
            id_tablaSubConWhere.add("id_ciudad");
            ObservableList<String> datosComboValues=con.LlenarCommbo("select id_ciudad from ciudad order by id_ciudad");            
            valoresWhere.add(datosComboValues.get(combo.getSelectionModel().getSelectedIndex()));
        }
        
        if(combo_empresa.getValue().equals("Todos"))
        {
        }
        else
        {
             tablaSubConWhere.add("empresa");
             id_tablaSubConWhere.add("id_empresa");
             ObservableList<String> datosComboEmpresa=con.LlenarCommbo("select id_empresa from empresa order by id_empresa");
             valoresWhere.add(datosComboEmpresa.get(combo_empresa.getSelectionModel().getSelectedIndex()));
        }
        
        if(combo_medida.getValue().equals("Todos"))
        {
        }
        else
        {
             tablaSubConWhere.add("medida");
             id_tablaSubConWhere.add("id_medida");
             ObservableList<String> datosComboMedida=con.LlenarCommbo("select id_medida from medida order by id_medida");
             valoresWhere.add(datosComboMedida.get(combo_medida.getSelectionModel().getSelectedIndex()));
        }
        
        if(combo_franja.getValue().equals("Todos"))
        {
        }
        else
        {
        }
        
        String[] tablaSubConWhereA = new String[tablaSubConWhere.size()];
        tablaSubConWhereA = tablaSubConWhere.toArray(tablaSubConWhereA);
        
        String [] id_tablaSubConWhereA = new String[id_tablaSubConWhere.size()];
        id_tablaSubConWhereA = id_tablaSubConWhere.toArray(id_tablaSubConWhereA);
        
        String [] valoresWhereA = new String[valoresWhere.size()];
        valoresWhereA = valoresWhere.toArray(valoresWhereA);
        
        String SQL = "";
        SQL += consul.generarSQL(combo_tipo.getValue().toString().toUpperCase(), tipo_filtro.getValue().toString(), "id_"+tipo_filtro.getValue().toString(), tipo_filtro.getValue().toString(), tablaSubConWhereA, id_tablaSubConWhereA, valoresWhereA);
        System.out.print(SQL);
        
        ObservableList<PieChart.Data> pieChartData = con.EjecutarConsultaPieChart(SQL);                
   

        
         ((Parent) mibarchar).getStylesheets().add(drilldownCss);
        
        mibarchar.setData(pieChartData);      
    }
    public void evento(){
    
      
   // final Label caption = new Label("");
    caption.setTextFill(Color.DARKORANGE);
    caption.setStyle("-fx-font: 29 arial;");
    
    assert caption != null : "fx:id=\"caption\" was not injected: check your FXML file 'FXMLDocumetn.fxml'.";  
    
    
        for (final PieChart.Data data : mibarchar.getData()) {
    
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED , new EventHandler<MouseEvent>() {
    
                @Override public void handle(MouseEvent e) {
                        
                        caption.setTranslateX(e.getSceneX());                        
                        caption.setTranslateY(e.getSceneY());
                        
                        System.out.println("=======>"+data.getPieValue());
                        caption.setText(String.valueOf(data.getPieValue()) + "%");
                        
                     }
                });
        }
    
    
    }
  
    
    @FXML private void reporteLine(ActionEvent E){
        ObservableList<XYChart.Series<Double, Double>> lineChartData = FXCollections.observableArrayList();  
        LineChart.Series<Double, Double> series = new LineChart.Series<Double, Double>(); 
        LineChart.Series<Double, Double> series3 = new LineChart.Series<Double, Double>();
       
        series.setName("Portfolio 1");
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
       
        
        series3.setName("Portfolio 2");
        series3.getData().add(new XYChart.Data(12, 44));
        series3.getData().add(new XYChart.Data(11, 35));
        series3.getData().add(new XYChart.Data(10, 36));
        series3.getData().add(new XYChart.Data(9, 33));
        series3.getData().add(new XYChart.Data(8, 31));
        series3.getData().add(new XYChart.Data(7, 26));
        series3.getData().add(new XYChart.Data(6, 22));
        series3.getData().add(new XYChart.Data(5, 25));
        series3.getData().add(new XYChart.Data(4, 43));
        series3.getData().add(new XYChart.Data(3, 44));
        series3.getData().add(new XYChart.Data(2, 45));
        series3.getData().add(new XYChart.Data(1, 44));
       
        
        lineChartData.addAll(series,series3);
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

   
   
   
}

