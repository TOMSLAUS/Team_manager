package controller;

import java.util.ArrayList;
import java.util.ResourceBundle;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Product;
import model.Product.Builder;



public class MainController implements Initializable, MapComponentInitializedListener {
	
	
	@FXML
	ObservableList<Product> data;
	@FXML
	private TableView<Product> productTable;
	@FXML
	private TableColumn<Product, String> nameColumn = new TableColumn<Product, String>();
	@FXML
	private TableColumn<Product, Double> priceColumn = new TableColumn<Product, Double>();
	@FXML
    private GoogleMapView mapView;
    
    private GoogleMap map;
    

    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        
        
        Builder product = new Product.Builder()
        		.name("Bulciņa")
        		.price(3.12);
        
    }    

    public void mapInitialized() {

        
        
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(56.954335, 24.117999))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);
                   
        map = mapView.createMap(mapOptions);
        ArrayList<Product> productList = new ArrayList<Product>();
        productList.add(new Product.Builder()
        		.name("builciņa")
        		.price(3.12)
        		.coordinates(new LatLong(56.953212, 24))
        		.build());
        
        
        productList.add(new Product.Builder()
        		.name("desa")
        		.price(3.12)
        		.coordinates(new LatLong(56.96, 24))
        		.build());
        
        
        productList.add(new Product.Builder()
        		.name("maize")
        		.price(3.12)
        		.coordinates(new LatLong(56.84, 24))
        		.build());
        
        
        
        
        
        
        //adds items to the table
        data = FXCollections.observableArrayList(productList);
        productTable.setItems(data);
 
        
        //adds markers to the map
        ArrayList<MarkerOptions> markerOptions = new ArrayList<MarkerOptions>();
        ArrayList<Marker> marker = new ArrayList<Marker>();
        ArrayList<InfoWindowOptions> infoWindowOptions = new ArrayList<InfoWindowOptions>();
       
 
        for(int i=0; i<productList.size();i++) {
        	markerOptions.add(new MarkerOptions());
        	markerOptions.set(i, markerOptions.get(i).position(productList.get(i).getCoordinates()));
        	marker.add(new Marker(markerOptions.get(i)));
        	 map.addMarker(marker.get(i));
        /*
        	 infoWindowOptions.add(new InfoWindowOptions());
        	 infoWindowOptions.set(i, infoWindowOptions.get(i).content("<h2>" + productList.get(i).getName() + "</h2>"
        			 + "Cena: " + productList.get(i).getPrice()));
        	 InfoWindow windoww = new InfoWindow(infoWindowOptions.get(0));
        	 windoww.open(map, marker.get(0));
*/

        }
        


    }
}
