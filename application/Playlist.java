package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Playlist {

	public static void OpenPlaylist() {

		Stage playlist = new Stage();
		playlist.initModality(Modality.APPLICATION_MODAL);

		Pane pane = new Pane();

		Scene scene = new Scene(pane, 100, 360);

		Button closebtn = new Button("Close playlist");
		Button addm = new Button("Add");
		Button delm = new Button("Delete");

		pane.getChildren().add(closebtn);
		pane.getChildren().add(addm);
		pane.getChildren().add(delm);

		closebtn.setPrefSize(100, 30);
		closebtn.setLayoutX(620);
		closebtn.setLayoutY(450);
		addm.setPadding(new Insets(1));

		addm.setPrefSize(60, 30);
		addm.setLayoutX(660);
		addm.setLayoutY(0);
		addm.setPadding(new Insets(1));

		delm.setPrefSize(60, 30);
		delm.setLayoutX(660);
		delm.setLayoutY(30);
		addm.setPadding(new Insets(1));

		playlist.setScene(scene);
		playlist.setTitle("Playlist");
		playlist.showAndWait();
		playlist.show();

		closebtn.setOnAction(event -> playlist.close());

	}

}