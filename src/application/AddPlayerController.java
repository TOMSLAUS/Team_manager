package application;

import database.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddPlayerController {



	@FXML
	private AnchorPane addPlayerAnchorPane;
	@FXML
	private DatePicker addPlayerBirthday;
	@FXML
	private TextField addPlayerName;
	@FXML
	private TextField addPlayerLastname;
	@FXML
	private TextField addPlayerJerseyNumber;
	@FXML
	private TextField addPlayerPhoneNumber;
	@FXML
	private TextField addPlayerPricePerTime;
	@FXML
	private MainController mainWindow;
	@FXML
	private Label speletajsSaglabats;
	@FXML
	private Button addPlayerButton;




	@FXML
	void addPlayerFunction(ActionEvent event) {

		if ((addPlayerName.getText().trim().isEmpty()) 
				|| (addPlayerLastname.getText().trim().isEmpty())
				|| (addPlayerPhoneNumber.getText().trim().isEmpty())
				|| (addPlayerJerseyNumber.getText().trim().isEmpty()) 
				|| (addPlayerBirthday.getValue() == null)
				|| (addPlayerPricePerTime.getText().trim().isEmpty())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Please fill all of the fields!");


			alert.showAndWait();
			return;
			// #TODO izveidot logu, kurā izmet "ievadi vārdu vai uzvārdu..."
		}

		// LocalDate lc=addPlayerBirthday.getValue();
		Player pl = new Player.Builder().setName(addPlayerName.getText()).setLastName(addPlayerLastname.getText())
				.setPhoneNumber(addPlayerPhoneNumber.getText()).setJerseyNumber(addPlayerJerseyNumber.getText())
				.setBirthDay(addPlayerBirthday.getValue()).setPricePerTime(Integer.parseInt(addPlayerPricePerTime.getText())).build();

		// pl.convertDateToStr(lc);
		pl.addNewPlayer();
		addPlayerName.setText("");
		addPlayerLastname.setText("");
		addPlayerPhoneNumber.setText("");
		addPlayerJerseyNumber.setText("");
		addPlayerPricePerTime.setText("");
		addPlayerBirthday.setValue(null);
		

	}




}