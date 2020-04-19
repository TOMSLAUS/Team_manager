package controller;

import DAO.PlayerDAO;
import model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EditPlayerContoller {


	@FXML
	private TextField editPlayerName;
	@FXML
	private TextField editPlayerLastName;
	@FXML
	private TextField editPlayerPhoneNumber;
	@FXML
	private TextField editPlayerJerseyNumber;
	@FXML
	private DatePicker editPlayerBirthday;
	@FXML
	private AnchorPane editPlayer;
	@FXML
	private Button editPlayerButton;

	private PlayerDAO playerDAO = new PlayerDAO();
	private Player editablePlayer;

	
	
	
	@FXML
	public void initialize() {
		editablePlayer= MainController.getSelectedPlayer();
		editPlayerName.setText(editablePlayer.getName());
		editPlayerLastName.setText(editablePlayer.getLastName());
		editPlayerPhoneNumber.setText(editablePlayer.getPhoneNumber());
		editPlayerJerseyNumber.setText(editablePlayer.getJerseyNumber());
		editPlayerBirthday.setValue(editablePlayer.getBirthDay());
	}
	
	
	
	
	@FXML
	void saveEditedPlayer(ActionEvent event) {
		if ((editPlayerName.getText().trim().isEmpty())
				|| (editPlayerLastName.getText().trim().isEmpty())
				|| (editPlayerPhoneNumber.getText().trim().isEmpty())
				|| (editPlayerJerseyNumber.getText().trim().isEmpty())
				|| (editPlayerBirthday.getValue() == null)) {
			MainController.showError("Error", "Please fill all of the fields!");
			return;
		}
		else {
			editablePlayer.setName(editPlayerName.getText());
			editablePlayer.setLastName(editPlayerLastName.getText());
			editablePlayer.setPhoneNumber(editPlayerPhoneNumber.getText());
			editablePlayer.setJerseyNumber(editPlayerJerseyNumber.getText());
			editablePlayer.setBirthDay(editPlayerBirthday.getValue());
			String[] params = new String[1];
			playerDAO.update(editablePlayer, params);

		    Stage stage = (Stage) editPlayerButton.getScene().getWindow();
		    // do what you have to do
		    stage.close();
		}
	}

	

}
