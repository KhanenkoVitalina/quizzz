package application;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BackgroundMusic implements Runnable{
	 MediaPlayer mediaPlayer;
	 boolean play;
	public BackgroundMusic() {
		String path = "music.MP3";
		Media media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
		Thread th = new Thread(this);
		th.start();
	}
	@Override
	public void run() {
		mediaPlayer.play();
		play = true;
		
	}
	public void pause() {
		if(play) {
		mediaPlayer.pause();
		play = false;
		} else {
			mediaPlayer.play();
			play = true;
		}
	}
	

}
