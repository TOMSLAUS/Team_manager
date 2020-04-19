import java.io.IOException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.DAO;
import model.Player;
import model.Player.PlayerBuilder;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {


	@Override
	public void start(Stage primaryStage) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/loginScreen.fxml"));
		Parent root = loader.load();

		Scene scene = new Scene(root);
		primaryStage.setTitle("Log in");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}

