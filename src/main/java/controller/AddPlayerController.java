package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import com.sun.javafx.logging.Logger;

import DAO.PlayerDAO;
import model.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

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
	private Label speletajsSaglabats;
	@FXML
	private Button addPlayerButton;
	@FXML
	private Button pictureButton;
	/*
	@FXML
	FileChooser fileChooser = new FileChooser();
	Window owner = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
	 
	
@FXML
void getPicture(ActionEvent event) {
	  File selectedFile = fileChooser.showOpenDialog(owner);
}
	*/ 
	@FXML
	void addPlayerFunction(ActionEvent event) {

		PlayerDAO playerDAO = new PlayerDAO();
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
			// #TODO izveidot logu, kurÄ� izmet "ievadi vÄ�rdu vai uzvÄ�rdu..."
		}

		// LocalDate lc=addPlayerBirthday.getValue();
		Player pl = new Player.PlayerBuilder()
				.setName(addPlayerName.getText())
				.setLastName(addPlayerLastname.getText())
				.setPhoneNumber(addPlayerPhoneNumber.getText())
				.setJerseyNumber(addPlayerJerseyNumber.getText())
				.setBirthDay(addPlayerBirthday.getValue())
				.setPricePerTime(Integer.parseInt(addPlayerPricePerTime.getText()))
				.build();

		// pl.convertDateToStr(lc);
		playerDAO.save(pl);
		addPlayerName.setText("");
		addPlayerLastname.setText("");
		addPlayerPhoneNumber.setText("");
		addPlayerJerseyNumber.setText("");
		addPlayerPricePerTime.setText("");
		addPlayerBirthday.setValue(null);

	}
}