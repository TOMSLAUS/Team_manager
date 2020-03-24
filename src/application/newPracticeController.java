package application;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import database.Player;
import database.Practice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class newPracticeController {
	Node topNode;
	ObservableList<Node> childs;
	ObservableList<Player> data;
	ObservableList<Player> data41 = FXCollections.observableArrayList();
	ObservableList<Player> data42 = FXCollections.observableArrayList();
	ObservableList<Player> data43 = FXCollections.observableArrayList();
	ObservableList<Player> data44 = FXCollections.observableArrayList();
	ObservableList<Player> data31 = FXCollections.observableArrayList();
	ObservableList<Player> data32 = FXCollections.observableArrayList();
	ObservableList<Player> data33 = FXCollections.observableArrayList();
	ObservableList<Player> data21 = FXCollections.observableArrayList();
	ObservableList<Player> data22 = FXCollections.observableArrayList();
	ObservableList<Player> practiceData41;
	ObservableList<Player> practiceData42;
	ObservableList<Player> practiceData43;
	ObservableList<Player> practiceData44;
	ObservableList<Player> practiceData31;
	ObservableList<Player> practiceData32;
	ObservableList<Player> practiceData33;
	ObservableList<Player> practiceData21;
	ObservableList<Player> practiceData221;
	ObservableList<String> practiceData;
	static ObservableList<Player> selectedPlayers;


	@FXML
	private RadioButton radio4Lines;
	@FXML
	private RadioButton radio3Lines;
	@FXML
	private RadioButton radio2Lines;
	@FXML
	private ToggleGroup toggleGroup;
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
	private TableView<Player> practicePlayerTable41;
	@FXML
	private TableView<Player> practicePlayerTable42;
	@FXML
	private TableView<Player> practicePlayerTable43;
	@FXML
	private TableView<Player> practicePlayerTable44;
	@FXML
	private TableView<Player> practicePlayerTable31;
	@FXML
	private TableView<Player> practicePlayerTable32;
	@FXML
	private TableView<Player> practicePlayerTable33;
	@FXML
	private TableView<Player> practicePlayerTable21;
	@FXML
	private TableView<Player> practicePlayerTable22;
	@FXML
	private TableColumn<Player, String> column41;
	@FXML
	private TableColumn<Player, String> column42;
	@FXML
	private TableColumn<Player, String> column43;
	@FXML
	private TableColumn<Player, String> column44;
	@FXML
	private TableColumn<Player, String> column31;
	@FXML
	private TableColumn<Player, String> column32;
	@FXML
	private TableColumn<Player, String> column33;
	@FXML
	private TableColumn<Player, String> column21;
	@FXML
	private TableColumn<Player, String> column22;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Pane lines4Pane;
	@FXML
	private Pane lines3Pane;
	@FXML
	private Pane lines2Pane;
	@FXML
	private StackPane stackPane;
	@FXML
	private DatePicker practiceDatePicker;




	@FXML
	private void initialize() {

		ToggleGroup toggleGroup = new ToggleGroup();
		radio4Lines.setToggleGroup(toggleGroup);
		radio3Lines.setToggleGroup(toggleGroup);
		radio2Lines.setToggleGroup(toggleGroup);


/////////////////////////////////////////////////////
/////////////////////////////////////////////////////
/////////////////////////////////////////////////////
/////////////////////////////////////////////////////
/////////////////////////////////////////////////////

		// dont mind this code////
/////////////////////////////////////////////////////
/////////////////////////////////////////////////////
/////////////////////////////////////////////////////
/////////////////////////////////////////////////////
/////////////////////////////////////////////////////



		playerTable.setOnMouseClicked(new EventHandler<MouseEvent>() { // click
			@Override
			public void handle(MouseEvent event) {


				Player selected = playerTable.getSelectionModel().getSelectedItem();
				if (selected != null) {
					System.out.println("select : " + selected.getName()+selected.getPlayerId());


				}
			}
		});

		playerTable.setOnDragDetected(new EventHandler<MouseEvent>() { // drag
			@Override
			public void handle(MouseEvent event) {
				// drag was detected, start drag-and-drop gesture
				Player selected = playerTable.getSelectionModel().getSelectedItem();
				if (selected != null) {

					Dragboard db = playerTable.startDragAndDrop(TransferMode.ANY);
					ClipboardContent content = new ClipboardContent();
					content.put(MainController.format, selected);
					db.setContent(content);
					event.consume();
				}
			}
		});

		practicePlayerTable41.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				// data is dragged over the target
				Dragboard db = event.getDragboard();
				if (event.getDragboard().hasContent(MainController.format)) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		practicePlayerTable41.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (event.getDragboard().hasContent(MainController.format)) {

					Player pl = (Player) db.getContent(MainController.format);
					data41.add(pl);
					success = true;
					practicePlayerTable41.setItems(data41);


				}
				event.setDropCompleted(success);
				event.consume();
			}
		});

//////


		practicePlayerTable42.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				// data is dragged over the target
				Dragboard db = event.getDragboard();
				if (event.getDragboard().hasContent(MainController.format)) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		practicePlayerTable42.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (event.getDragboard().hasContent(MainController.format)) {

					Player pl = (Player) db.getContent(MainController.format);
					data42.add(pl);
					success = true;
					practicePlayerTable42.setItems(data42);


				}
				event.setDropCompleted(success);
				event.consume();
			}
		});


///////
		practicePlayerTable43.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				// data is dragged over the target
				Dragboard db = event.getDragboard();
				if (event.getDragboard().hasContent(MainController.format)) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		practicePlayerTable43.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (event.getDragboard().hasContent(MainController.format)) {

					Player pl = (Player) db.getContent(MainController.format);
					data43.add(pl);
					success = true;
					practicePlayerTable43.setItems(data43);


				}
				event.setDropCompleted(success);
				event.consume();
			}
		});


////////
		practicePlayerTable44.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				// data is dragged over the target
				Dragboard db = event.getDragboard();
				if (event.getDragboard().hasContent(MainController.format)) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		practicePlayerTable44.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (event.getDragboard().hasContent(MainController.format)) {

					Player pl = (Player) db.getContent(MainController.format);
					data44.add(pl);
					success = true;
					practicePlayerTable44.setItems(data44);


				}
				event.setDropCompleted(success);
				event.consume();
			}
		});
////////


		practicePlayerTable31.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				// data is dragged over the target
				Dragboard db = event.getDragboard();
				if (event.getDragboard().hasContent(MainController.format)) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		practicePlayerTable31.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (event.getDragboard().hasContent(MainController.format)) {

					Player pl = (Player) db.getContent(MainController.format);
					data31.add(pl);
					success = true;
					practicePlayerTable31.setItems(data31);


				}
				event.setDropCompleted(success);
				event.consume();
			}
		});

//////////////
		practicePlayerTable32.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				// data is dragged over the target
				Dragboard db = event.getDragboard();
				if (event.getDragboard().hasContent(MainController.format)) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		practicePlayerTable32.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (event.getDragboard().hasContent(MainController.format)) {

					Player pl = (Player) db.getContent(MainController.format);
					data32.add(pl);
					success = true;
					practicePlayerTable32.setItems(data32);


				}
				event.setDropCompleted(success);
				event.consume();
			}
		});



/////////////
		practicePlayerTable33.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				// data is dragged over the target
				Dragboard db = event.getDragboard();
				if (event.getDragboard().hasContent(MainController.format)) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		practicePlayerTable33.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (event.getDragboard().hasContent(MainController.format)) {

					Player pl = (Player) db.getContent(MainController.format);
					data33.add(pl);
					success = true;
					practicePlayerTable33.setItems(data33);


				}
				event.setDropCompleted(success);
				event.consume();
			}
		});

////////////////
		practicePlayerTable21.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				// data is dragged over the target
				Dragboard db = event.getDragboard();
				if (event.getDragboard().hasContent(MainController.format)) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		practicePlayerTable21.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (event.getDragboard().hasContent(MainController.format)) {

					Player pl1 = (Player) db.getContent(MainController.format);
					data21.add(pl1);
					success = true;
					practicePlayerTable21.setItems(data21);


				}
				event.setDropCompleted(success);
				event.consume();
			}
		});

/////////////
		practicePlayerTable22.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				// data is dragged over the target
				Dragboard db = event.getDragboard();
				if (event.getDragboard().hasContent(MainController.format)) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		practicePlayerTable22.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (event.getDragboard().hasContent(MainController.format)) {

					Player pl = (Player) db.getContent(MainController.format);
					data22.add(pl);
					success = true;
					practicePlayerTable22.setItems(data22);


				}
				event.setDropCompleted(success);
				event.consume();
			}
		});




		TableViewSelectionModel<Player> selectionModel = playerTable.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		selectedPlayers = selectionModel.getSelectedItems();

		playerTable.setPlaceholder(new Label("No rows to display"));
		ArrayList<Player> playerList = new ArrayList<Player>();
		// Player player = new Player();
		Connection con = Player.connect();
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
			MainController.showError("Error 0008", "Please try again.");
		}


		nameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("phoneNumber"));
		birthDayColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("birthDayStr"));
		playerTable.setItems(data);




		column41.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		column42.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		column43.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		column44.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		column31.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		column32.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		column33.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		column21.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		column22.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));


		childs = stackPane.getChildren();



		topNode = childs.get(childs.size() - 1);
		topNode.toFront();




	}



	@FXML
	void select4Line(ActionEvent event) {
		lines2Pane.setVisible(false);
		lines3Pane.setVisible(false);
		lines4Pane.setVisible(true);
	}

	@FXML
	void select3Line(ActionEvent event) {
		lines2Pane.setVisible(false);
		lines4Pane.setVisible(false);
		lines3Pane.setVisible(true);
	}

	@FXML
	void select2Line(ActionEvent event) {
		lines4Pane.setVisible(false);
		lines3Pane.setVisible(false);
		lines2Pane.setVisible(true);
	}



	@FXML
	void createPractice(ActionEvent event) {


		if (lines4Pane.isVisible()) {
			takeSnapShot(lines4Pane);
			Practice practice = new Practice();



			for (Player pla : practicePlayerTable41.getItems()) {
				practice.players41.add(pla);
			}

			for (Player pla : practicePlayerTable42.getItems()) {
				practice.players42.add(pla);
			}

			for (Player pla : practicePlayerTable43.getItems()) {
				practice.players43.add(pla);
			}

			for (Player pla : practicePlayerTable44.getItems()) {
				practice.players44.add(pla);
			}

			if (practiceDatePicker.getValue() == null) {
				MainController.showError("Error", "To continue you need to select date");
			}
			else if (practice.players41.size() < 1||practice.players42.size()<1 || practice.players43.size()<1 || practice.players44.size()<1) {
				MainController.showError("Error", "To continue you need to add players to the practice lineup.");
			} else {
				practice.addNewPractice4(practiceDatePicker.getValue());
				takeSnapShot(lines4Pane);
			}
			
		}




		if (lines3Pane.isVisible()) {
			takeSnapShot(lines3Pane);
			Practice practice = new Practice();
			for (Player pla : practicePlayerTable31.getItems()) {
				practice.players31.add(pla);
			}

			for (Player pla : practicePlayerTable32.getItems()) {
				practice.players32.add(pla);
			}

			for (Player pla : practicePlayerTable33.getItems()) {
				practice.players33.add(pla);
			}
			
			if (practiceDatePicker.getValue() == null) {
				MainController.showError("Error", "To continue you need to select date");
			}
			else if (practice.players31.size() < 1||practice.players32.size()<1 || practice.players33.size()<1) {
				MainController.showError("Error", "To continue you need to add players to the practice lineup.");
			} else {
				practice.addNewPractice3(practiceDatePicker.getValue());
				takeSnapShot(lines3Pane);
			}
		}



		if (lines2Pane.isVisible()) {
			Practice practice = new Practice();


			for (Player pla : practicePlayerTable21.getItems()) {
				practice.players21.add(pla);
			}

			for (Player pla : practicePlayerTable22.getItems()) {
				practice.players22.add(pla);
			}
			if (practiceDatePicker.getValue() == null) {
				MainController.showError("Error", "To continue you need to select date");
			}
			else if (practice.players21.size() < 1||practice.players22.size()<1) {
				MainController.showError("Error", "To continue you need to add players to the practice lineup.");
			} else {
				practice.addNewPractice2(practiceDatePicker.getValue());
				takeSnapShot(lines2Pane);
			}
		}
	}




	private void takeSnapShot(Pane pane) {
		String desktopPath = System.getProperty("user.home") + "/Desktop";
		System.out.print(desktopPath.replace("\\", "/"));
		WritableImage writableImage = new WritableImage((int) pane.getWidth(), (int) pane.getHeight());
		pane.snapshot(null, writableImage);

		File file = new File(desktopPath + "\\practice.png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
			System.out.println("snapshot saved: " + file.getAbsolutePath());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}



}
