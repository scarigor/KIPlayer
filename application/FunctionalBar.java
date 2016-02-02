package application;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class FunctionalBar extends HBox {

	Slider time = new Slider();
	Slider vol = new Slider();

	Button playButton = new Button("");
	Button stopButton = new Button("");
	Button speedUpButton = new Button("");
	Button muteButton = new Button("");

	Button speedDownButton = new Button("");

	Label volume = new Label("Vol");

	MediaPlayer player;

	public FunctionalBar(MediaPlayer play) {
		
		
		player = play;
		setAlignment(Pos.CENTER);
		setPadding(new Insets(5, 10, 5, 10));

		vol.setPrefWidth(70);
		vol.setMinWidth(30);
		vol.setValue(100);

		HBox.setHgrow(time, Priority.ALWAYS);
		playButton.setPrefSize(50, 20);
		playButton.setPadding((new Insets(1)));
		playButton.setText("Pause");

		stopButton.setPrefSize(20, 20);
		stopButton.setPadding((new Insets(1, 2, 1, 2)));
		stopButton.setText("■");
		
		muteButton.setText("Mute");
		muteButton.setPrefSize(30, 20); 
		muteButton.setPadding(new Insets(1));
		
		speedUpButton.setText("▸▸");
		speedUpButton.setPrefSize(30, 20); 
		speedUpButton.setPadding(new Insets(1)); 

		speedDownButton.setPrefSize(30, 20); 
		speedDownButton.setPadding(new Insets(1));
		speedDownButton.setText("◂◂");
		

		getChildren().add(speedDownButton);
		getChildren().add(playButton);
		getChildren().add(stopButton);
		getChildren().add(speedUpButton);
		getChildren().add(time);
		getChildren().add(volume);
		getChildren().add(vol);
		
		
		getChildren().add(muteButton);

		muteButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (vol.getValue() != 0) {
					player.setVolume(0);
					muteButton.setText("Unmute");
					vol.setValue(0);
				} else {
					player.setVolume(100);
					vol.setValue(100);
					muteButton.setText("Mute");
				}
			}
		});

		speedUpButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				player.setRate(player.getRate() * 1.2);
				player.getRate();
			}
		});

		speedDownButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				player.setRate(player.getRate() / 1.2);
				player.getRate();
			}
		});

		stopButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (player.getStatus() == Status.PLAYING || (player.getStatus() == Status.PAUSED)) {
					player.stop();
					player.setRate(1);
					playButton.setText("Play");
					time.setValue(0);
				}
			}
		});

		playButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Status status = player.getStatus();

				if (status == Status.PLAYING) {
					if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {
						player.seek(player.getStartTime());
						player.play();
					} else {
						player.pause();
						playButton.setText("Play");
					}
				}

				if (status == Status.PAUSED || status == Status.HALTED || status == Status.STOPPED) {
					player.play();
					playButton.setText("Pause");
				}
			}
		});

		player.currentTimeProperty().addListener(new InvalidationListener() {

			public void invalidated(Observable observable) {
				updateValues();

			}
		});

		time.valueProperty().addListener(new InvalidationListener() {

			public void invalidated(Observable observable) {
				if (time.isPressed()) {
					player.seek(player.getMedia().getDuration().multiply(time.getValue() / 100));
				}
			}
		});
		
		vol.valueProperty().addListener(new InvalidationListener() {

			public void invalidated(Observable observable) {
				if (vol.isPressed()) {
					player.setVolume(vol.getValue()/100);
				}
			}
		});
	}

	protected void updateValues() {
		Platform.runLater(new Runnable() {
			public void run() {
				time.setValue(player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100);
			}
		});
	}
}
