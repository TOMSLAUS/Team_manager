package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import DAO.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Player;
import model.Practice;
import model.PracticeAttendance;
import model.User;

public class pastPracticesController {
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TableView<Practice> practiceTable;
	@FXML
	private TableColumn<LocalDate, String> column1 = new TableColumn<LocalDate, String>();
	@FXML
	private TableColumn<String, String> column2 = new TableColumn<String, String>();
	@FXML
	private TableView<Player> playerTable;
	@FXML
	private TableColumn<Player, String> column21 = new TableColumn<Player, String>();
	@FXML
	private TableColumn<Player, Integer> column22 = new TableColumn<Player, Integer>();

	User user = LoginController.user;
	ObservableList<Practice> data;
	ObservableList<Player> playerData;
	static ObservableList<Practice> selectedPractice;

	@FXML
	public void initialize() {

		column1.setSortType(TableColumn.SortType.DESCENDING);
		TableView.TableViewSelectionModel<Practice> selectionModel = practiceTable.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		selectedPractice = selectionModel.getSelectedItems();

		ArrayList<Practice> practiceList = new ArrayList<Practice>();
		PreparedStatement st;

		try {

			st = DAO.connect().prepareStatement("select * from Practice WHERE teamId=?");
			st.setInt(1, user.getTeamId());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {

				practiceList.add(new Practice.Builder().setPracticeId(rs.getInt(1))
						.setPracticeDate(rs.getDate(2).toLocalDate()).setNumberOfLines(rs.getInt(3)).build());
			}
			data = FXCollections.observableArrayList(practiceList);
		} catch (SQLException e) {
			e.printStackTrace();
			// showError("Error 0004", "Please try again.");
		}

		column1.setCellValueFactory(new PropertyValueFactory<LocalDate, String>("practiceDate"));
		column2.setCellValueFactory(new PropertyValueFactory<String, String>("numberOfLines"));
		practiceTable.setItems(data);

		if (!selectedPractice.isEmpty()) {
			fillPlayerTable();
		}

	}

	public void fillPlayerTable() {

		ArrayList<PracticeAttendance> practiceAttendanceList = new ArrayList<PracticeAttendance>();
		ArrayList<Player> playerList = new ArrayList<Player>();

		PreparedStatement st;
		try {
			st = DAO.connect().prepareStatement("SELECT * FROM players WHERE practiceId=?;");
			st = DAO.connect().prepareStatement("SELECT * FROM practiceAttendance WHERE practiceId=?;");
			st.setInt(1, selectedPractice.get(0).getPracticeId());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				practiceAttendanceList.add(new PracticeAttendance.Builder().setPracticeId(rs.getInt(1))
						.setPlayerId(rs.getInt(2)).setLine(rs.getInt(3)).build());
				// practiceAttendees.put(rs.getInt(1), rs.getInt(2));
			}

			st = DAO.connect().prepareStatement("SELECT * FROM players WHERE playerId=?;");

			for (int i = 0; i < practiceAttendanceList.size(); i++) {
				st.setInt(1, practiceAttendanceList.get(i).getPlayerId());
				rs = st.executeQuery();

				while (rs.next()) {
					playerList.add(new Player.PlayerBuilder().setPlayerId(rs.getInt(1)).setName(rs.getString(3))
							.setLastName(rs.getString(4)).setPhoneNumber(rs.getString(6))
							.setJerseyNumber(rs.getString(7)).setBirthDay(rs.getDate(12).toLocalDate())
							.setBirthDayStr(rs.getString(12)).build());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// showError("Error 0004", "Please try again.");
		}

		for (int i = 0; i < practiceAttendanceList.size(); i++) {
			for (int j = 0; j < playerList.size(); j++) {
				if (practiceAttendanceList.get(i).getPlayerId() == playerList.get(j).getPlayerId()) {
					playerList.get(j).setLine(practiceAttendanceList.get(i).getLine());
				}
			}
		}

		playerData = FXCollections.observableArrayList(playerList);
		column21.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		column22.setCellValueFactory(new PropertyValueFactory<Player, Integer>("line"));
		playerTable.setItems(playerData);
		playerTable.refresh();

	}

}
