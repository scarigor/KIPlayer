package application;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Player extends BorderPane {
	
	
	Media media;
	MediaPlayer mPlayer;
	MediaView view;
	Pane impane,mpane;
	FunctionalBar bar;
	public Player(String file){
		media = new Media(file);
		mPlayer = new MediaPlayer(media);
		view = new MediaView(mPlayer);
		
		mpane = new Pane();
		mpane.getChildren().add(view);
		
		bar = new FunctionalBar(mPlayer);
		setCenter(mpane);
		setTop(bar);
		mPlayer.play();
		//bar.setSpacing(USE_PREF_SIZE);
		
	}
	
	public Player (Image img){
		
		ImageView iv1 = new ImageView();
        iv1.setImage(img);
        impane = new Pane();
        impane.getChildren().add(iv1);
        setCenter(impane);
        
        
	}
	

	
	
}
