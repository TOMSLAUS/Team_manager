package controller;

import java.io.IOException;

import DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

public class LoginController {

	@FXML
	private Pane loginPane;
	@FXML
	private Labeled emailLabel;
	@FXML
	private Labeled passwordLabel;
	@FXML
	private TextField emailField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginButton;
	public static User user = new User();
	private UserDAO userDAO = new UserDAO();



	@FXML
	void check(ActionEvent event) {

		user.setEmail(emailField.getText());
		user.setPassword(passwordField.getText());

		if (userDAO.getUser(user)) {
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Team manager");
				stage.setScene(new Scene(root, 825, 780));
				stage.show();
				// Hides current window
				((Node) event.getSource()).getScene().getWindow().hide();

			} catch (IOException e) {
				// showError("Error 0002", "Please try again.");
				e.printStackTrace();
			}

		}

	}

}