package controller;

import DAO.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Player;
import model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class MainController {
	ObservableList<Player> data;
	static ObservableList<Player> selectedPlayers;
	public static DataFormat format = new DataFormat(
			"JAVA_DATAFLAVOR:application/x-my-mime-type; class=com.model.Player",
			"application/x-my-mime-type; class=com.model.Player");

	@FXML
	private Button pastPracticesButton;
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
	LoginController loginController = new LoginController();
	User user = LoginController.user;

	@FXML
	public void initialize() {

		TableView.TableViewSelectionModel<Player> selectionModel = playerTable.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		selectedPlayers = selectionModel.getSelectedItems();

		playerTable.setPlaceholder(new Label("No rows to display"));
		ArrayList<Player> playerList = new ArrayList<Player>();
		PreparedStatement st;

		try {

			st = DAO.connect().prepareStatement("select * from players WHERE teamId=?");
			st.setInt(1, user.getTeamId());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {

				playerList.add(new Player.PlayerBuilder().setPlayerId(rs.getInt(1)).setName(rs.getString(3))
						.setLastName(rs.getString(4)).setPhoneNumber(rs.getString(6)).setJerseyNumber(rs.getString(7))
						.setBirthDay(rs.getDate(12).toLocalDate()).setBirthDayStr(rs.getString(12)).build());
			}
			data = FXCollections.observableArrayList(playerList);
		} catch (SQLException e) {
			e.printStackTrace();
			showError("Error 0004", "Please try again.");
		}

		nameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("phoneNumber"));
		birthDayColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("birthDayStr"));
		playerTable.setItems(data);

	/*	
		playerTable.setRowFactory(tv -> {
			TableRow<Player> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Player rowData = row.getItem();
					System.out.println(rowData.getPlayerId());

					Parent root;

					try {
						root = FXMLLoader.load(getClass().getResource("/fxml/pastPractice.fxml"));
						Stage stage = new Stage();
						stage.setTitle("Past practices");
						stage.setScene(new Scene(root, 750, 750));
						stage.show();
					} catch (IOException e) {
						showError("Error 0002", "Please try again.");
					}
				}
			});
			return row;
		});*/

	}

	public static void showError(String headerText, String contentText) {

		Alert alert = new Alert(Alert.AlertType.ERROR);
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
			root = FXMLLoader.load(getClass().getResource("/fxml/addPlayer.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Add new player");
			stage.setScene(new Scene(root, 450, 450));
			stage.show();
		} catch (Exception e) {
			showError("Error 0002", "Please try again.");
			e.printStackTrace();
		}

	}

	@FXML
	void openPastPracticesWindow(ActionEvent event) {

		Parent root;

		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/pastPractices.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Practices");
			stage.setScene(new Scene(root, 755, 450));
			stage.show();
		} catch (Exception e) {
			//showError("Error 0002", "Please try again.");
			e.printStackTrace();
		}
	}

	@FXML
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
				root = FXMLLoader.load(getClass().getResource("/fxml/EditPlayer.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Edit player");
				stage.setScene(new Scene(root, 450, 450));
				stage.show();

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
			root = FXMLLoader.load(getClass().getResource("/fxml/newPractice.fxml"));
			Stage stage = new Stage();
			stage.setTitle("New practice");
			stage.setScene(new Scene(root, 850, 850));
			stage.show();

		} catch (IOException e) {
			showError("Error 0007", "Please try again.");
			e.printStackTrace();
		}
	}

}
