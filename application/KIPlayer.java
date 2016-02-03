package application;


import java.io.File;
import java.net.MalformedURLException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class KIPlayer extends BorderPane {
	
	
	Media media;
	MediaPlayer mPlayer;
	MediaView view;
	Pane stpane,mpane;
	FunctionalBar bar;
	public KIPlayer(String file){
		media = new Media(file);
		mPlayer = new MediaPlayer(media);
		view = new MediaView(mPlayer);
		
		mpane = new Pane();
		mpane.getChildren().add(view);
		
		bar = new FunctionalBar(mPlayer);
		setCenter(mpane);
		setTop(bar);
		mPlayer.play();
		mPlayer.setAutoPlay(true);
		
		DoubleProperty width = view.fitWidthProperty();
		DoubleProperty height = view.fitHeightProperty();
		width.bind(Bindings.selectDouble(view.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(view.sceneProperty(), "height"));
	}
	
//	public Player (Image img){
//		
//		ImageView iv1 = new ImageView();
//        iv1.setImage(img);
//        impane = new Pane();
//        impane.getChildren().add(iv1);
//        setCenter(impane);
//        
//        
//	}
	
	public KIPlayer(File file) { 
		if (file != null) { 
		try { 
		media = new Media(file.toURI().toURL().toExternalForm()); 
		mPlayer = new MediaPlayer(media); 
		view = new MediaView(mPlayer); 
		stpane = new Pane(); 

		stpane.getChildren().add(view); 

		setCenter(stpane); 

		setTop(bar);
		mPlayer.play(); 

		} catch (MalformedURLException e) { 

		e.printStackTrace(); 
		} 

		}
	

	}
	
}
