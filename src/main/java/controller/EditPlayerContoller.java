package main.java.controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.Main;
import main.java.DAO.PlayerDAO;
import main.java.model.Player;


public class EditPlayerContoller {

	private PlayerDAO playerdao=new PlayerDAO();
	private Player editablePlayer;
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


	
	
	
	@FXML
	public void initialize() {
		editablePlayer= Main.getSelectedPlayer();
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
			Main.showError("Error", "Please fill all of the fields!");
			return;			
		}
		else {
			
			editablePlayer.setName(editPlayerName.getText());
			editablePlayer.setLastName(editPlayerLastName.getText());
			editablePlayer.setPhoneNumber(editPlayerPhoneNumber.getText());
			editablePlayer.setJerseyNumber(editPlayerJerseyNumber.getText());
			editablePlayer.setBirthDay(editPlayerBirthday.getValue());
			playerdao.save(editablePlayer);;
			
		    Stage stage = (Stage) editPlayerButton.getScene().getWindow();
		    // do what you have to do
		    stage.close();
		}
	}
	

}
