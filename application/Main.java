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
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	KIPlayer player;
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
		// Image image = new
		// Image("file:///D:/OOP/Workspace/KIPlayer/src/application/res/load.jpg");
		File startLoading = new File("res/load.mp4");
		player = new KIPlayer(startLoading);
		player.mPlayer.setCycleCount(5);
		player.setBottom(menu);

		Scene scene = new Scene(player, 720, 480);

		primaryStage.setResizable(false);
		primaryStage.setTitle("KIPlayer");
		primaryStage.setScene(scene);
		primaryStage.show();

		open.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				
				File file = fileCh.showOpenDialog(primaryStage);
				if (file != null) {
					try {
						player.mPlayer.stop();
						player = new KIPlayer(file.toURI().toURL().toExternalForm());
						player.setBottom(menu);
						Scene scene = new Scene(player, 720, 480);
						primaryStage.setScene(scene);
						primaryStage.setResizable(true);
						primaryStage.setTitle(file.getName() + " - KIPlayer");
						player.view.setPreserveRatio(false);
						
						scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

							public void handle(MouseEvent event) {
								Status status = player.mPlayer.getStatus();

								if (event.getClickCount() == 1) {
									if (status == Status.PLAYING) {
										if (player.mPlayer.getCurrentTime().greaterThanOrEqualTo(player.mPlayer.getTotalDuration())) {
											player.mPlayer.seek(player.mPlayer.getStartTime());
											player.mPlayer.play();
										} else {
											player.mPlayer.pause();

										}
									}

									if (status == Status.PAUSED || status == Status.HALTED || status == Status.STOPPED) {
										player.mPlayer.play();

									}
								} else {
									if (((event.getClickCount() == 2)) && (primaryStage.isFullScreen() == true)) {

										primaryStage.setFullScreen(false);
									} else if ((event.getClickCount() == 2)) {
										primaryStage.setFullScreen(true);

									}
								}
							}
						});

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

		

		exit.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				primaryStage.close();

			}
		});

		playlist.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Playlist.OpenPlaylist();
			}
		});

	}

	public static void main(String[] args) {
		launch(args);
	}
}
