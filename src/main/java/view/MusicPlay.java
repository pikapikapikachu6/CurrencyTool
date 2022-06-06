package view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * For the background music
 */
public class MusicPlay {
    private static MediaPlayer mediaPlayer;
    private static Boolean isMusicPlay = false;

    /**
     * Used to: set up music
     */
    public static void musicSet() {
        Media media = new Media(new File("src/main/resource/music.wav").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(100);
    }

    /**
     * Used to play / stop music
     */
    public static void musicPlay(){
        isMusicPlay = !isMusicPlay;
        if (isMusicPlay) mediaPlayer.play();
        else mediaPlayer.pause();
    }
}
