package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import DAO.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Player;
import model.Practice;
import model.User;

public class pastPracticesController {
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TableView practiceTable;
	@FXML
	private TableColumn<LocalDate, String> column1 = new TableColumn<LocalDate, String>();
	@FXML
	private TableColumn<String, String> column2 = new TableColumn<String, String>();
	User user = LoginController.user;
	ObservableList<Practice> data;

	@FXML
	public void initialize() {
		ArrayList<Practice> practiceList = new ArrayList<Practice>();
		PreparedStatement st;

		try {

			st = DAO.connect().prepareStatement("select * from Practice WHERE teamId=?");
			st.setInt(1, user.getTeamId());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {

				practiceList.add(new Practice.Builder().setPracticeId(rs.getInt(1))
						.setPracticeDate(rs.getDate(2).toLocalDate())
						.setNumberOfLines(rs.getInt(3))
						.build());
			}
			data = FXCollections.observableArrayList(practiceList);
		} catch (SQLException e) {
			e.printStackTrace();
			// showError("Error 0004", "Please try again.");
		}

		column1.setCellValueFactory(new PropertyValueFactory<LocalDate, String>("practiceDate"));
		column2.setCellValueFactory(new PropertyValueFactory<String, String>("numberOfLines"));
		practiceTable.setItems(data);
	}

}
