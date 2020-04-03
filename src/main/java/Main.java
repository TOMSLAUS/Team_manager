package main.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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
import main.java.DAO.DAO;
import main.java.model.Player;




public class Main extends Application {

	ObservableList<Player> data;
	static ObservableList<Player> selectedPlayers;
	public static DataFormat format = new DataFormat(
			"JAVA_DATAFLAVOR:application/x-my-mime-type; class=com.main.java.model.Player",
			"application/x-my-mime-type; class=com.database.Player");

	@FXML
	private boolean loggedIn;
	@FXML
	private Button editPlayer;
	@FXML
	private Button deletePlayer;
	@FXML
	private Button addPlayer;
	@FXML
	private Button addPlayerSave;
	@FXML
	private ButtonBar buttonBar;
	@FXML
	private Button createPractice;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TableView<Player> playerTable;
	@FXML
	private TableColumn<Player, String> nameColumn = new TableColumn<Player, String>();
	@FXML
	private TableColumn<Player, String> lastNameColumn = new TableColumn<Player, String>();
	@FXML
	private TableColumn<Player, String> phoneNumberColumn = new TableColumn<Player, String>();
	@FXML
	private TableColumn<Player, String> birthDayColumn = new TableColumn<Player, String>();
	@FXML
	private Label label1;
	@FXML
	private Button refresh;
	private boolean loggedin;




	@FXML
	public void initialize() {
		TableViewSelectionModel<Player> selectionModel = playerTable.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		selectedPlayers = selectionModel.getSelectedItems();

		playerTable.setPlaceholder(new Label("No rows to display"));
		ArrayList<Player> playerList = new ArrayList<Player>();
		// Player player = new Player();
		Connection con = DAO.connect();
		Statement stmt;

		try {

			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from players");
			while (rs.next()) {

				playerList.add(new Player.Builder().setPlayerId(rs.getInt(1)).setName(rs.getString(3))
						.setLastName(rs.getString(4)).setPhoneNumber(rs.getString(6)).setJerseyNumber(rs.getString(7))
						.setBirthDay(rs.getDate(12).toLocalDate()).setBirthDayStr(rs.getString(12)).build());
			}
			data = FXCollections.observableArrayList(playerList);
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			showError("Error 0004", "Please try again.");
		}

		nameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("phoneNumber"));
		birthDayColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("birthDayStr"));
		playerTable.setItems(data);

	}


	public static void main(String[] args) {
		try {
		launch(args);
		}catch(Exception e) {
			System.out.println(e);
		}

	}



	@Override
	public void start(Stage primaryStage) throws IOException {

		//Parent root = FXMLLoader.load(getClass().getResource("C:\\Users\\risby\\Desktop\\Programming_2020\\Java\\intellij-projects\\github-contibutions\\TeamManager\\src\\main\\java\\Main.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
		//loader.setController(this);
		Parent root = loader.load();

		Scene scene = new Scene(root);
		primaryStage.setTitle("TeamManager");
		primaryStage.setScene(scene);
		primaryStage.show();

	}



	public static void showError(String headerText, String contentText) {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();

		// Uploads error with error code to database for error reporting
		if (headerText.matches(".*\\d+.*")) {

			PreparedStatement statement = null;

			try {

				statement = DAO.connect().prepareStatement("INSERT INTO Errors (errorId) VALUES (?)");
				;

				statement.setString(1, headerText);
				statement.executeUpdate();
			} catch (SQLException e) {
				// System.out.println("Error creating statement");
				e.printStackTrace();
			}
		}
	}



	@FXML
	void deletePlayer() {

		if (selectedPlayers.isEmpty()) {

			showError("Please select player you wish to delete!", null);

		} else {

			try {

				PreparedStatement prest = DAO.connect().prepareStatement("DELETE FROM players where playerId=?");
				prest.setInt(1, selectedPlayers.get(0).getPlayerId());
				prest.executeUpdate();
				initialize();
			} catch (SQLException e) {
				showError("Error 0001", "Please try again.");
				e.printStackTrace();
			}
			System.out.println("Record deleted successfully");
		}
	}




	@FXML
	void openAddPlayerWindow(ActionEvent event) {

		Parent root;

		try {
			root = FXMLLoader.load(getClass().getResource("/addPlayer.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Add new player");
			stage.setScene(new Scene(root, 450, 450));
			stage.show();
			// Paslēpt iepriekšējo logu
			// ((Node) event.getSource()).getScene().getWindow().hide();
		} catch (IOException e) {
			showError("Error 0002", "Please try again.");
		}

	}




	@FXML
	public
	static Player getSelectedPlayer() {
		return selectedPlayers.get(0);
	}


	@FXML
	void openEditPlayerWindow(ActionEvent event) {

		if (selectedPlayers.isEmpty()) {
			showError("Please select player you wish to edit!", null);
		} else {

			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("/EditPlayer.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Edit player");
				stage.setScene(new Scene(root, 450, 450));
				stage.show();
				// Paslēpt iepriekšējo logu
				// ((Node) event.getSource()).getScene().getWindow().hide();

			} catch (IOException e) {
				showError("Error 0003", "Please try again.");
				e.printStackTrace();
			}
		}
	}

	@FXML
	void openNewPracticeWindow(ActionEvent event) {

		Parent root;

		try {
			root = FXMLLoader.load(getClass().getResource("/newPractice.fxml"));
			Stage stage = new Stage();
			stage.setTitle("New practice");
			stage.setScene(new Scene(root, 850, 850));
			stage.show();
			// Paslēpt iepriekšējo logu
			// ((Node) event.getSource()).getScene().getWindow().hide();

		} catch (IOException e) {
			showError("Error 0007", "Please try again.");
			e.printStackTrace();
		}
	}




}

