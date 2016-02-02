package application;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	Player playerVAR;
	FileChooser fileCh;

	public void start(final Stage primaryStage) {

		MenuItem open = new MenuItem("Open");
		MenuItem fullscreen = new MenuItem("FullScreen");
		MenuItem exit = new MenuItem("Exit");
		MenuItem about = new MenuItem("About");
		MenuItem stop = new MenuItem("Stop");
		MenuItem play = new MenuItem("Play");
		MenuItem pause = new MenuItem("Pause");

		Menu file = new Menu("File");
		Menu playing = new Menu("Playing");
		Menu playlist = new Menu("Playlist");
		MenuBar menu = new MenuBar();

		file.getItems().add(open);
		file.getItems().add(about);
		file.getItems().add(exit);

		playing.getItems().add(stop);
		playing.getItems().add(play);
		playing.getItems().add(pause);
		playing.getItems().add(fullscreen);

		fileCh = new FileChooser();

		menu.getMenus().add(file);
		menu.getMenus().add(playing);
		menu.getMenus().add(playlist);
		primaryStage.setTitle("KIPlayer v.1.0");
	//	Image image = new Image("file:///D:/OOP/Workspace/KIPlayer/src/application/res/load.jpg");
		playerVAR = new Player("file:///D:/sample.mp4");

		playerVAR.setBottom(menu);

		Scene scene = new Scene(playerVAR, 720, 480);
		primaryStage.setScene(scene);
		primaryStage.show();

		open.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				File file = fileCh.showOpenDialog(primaryStage);
				if (file != null) {
					try {
						playerVAR = new Player(file.toURI().toURL().toExternalForm());
						Scene scene = new Scene(playerVAR, 720, 480);
						primaryStage.setScene(scene);
						primaryStage.show();
						primaryStage.sizeToScene();
						primaryStage.setTitle(file.getName() + " - KIPlayer v.1.0");
						playerVAR.setBottom(menu);

					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}

			}
		});

		fullscreen.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				primaryStage.setFullScreen(true);

			}

		});

		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				if (((event.getClickCount() == 2)) && (primaryStage.isFullScreen() == true)) {
					primaryStage.setFullScreen(false);
				} else if ((event.getClickCount() == 2))
					primaryStage.setFullScreen(true);

			}

		});

		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();

			}
		});

	}

	public static void main(String[] args) {
		launch(args);
	}
}
