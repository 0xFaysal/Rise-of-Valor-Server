package game.rise_of_valor_server.controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IntroController implements Initializable {

    @FXML
    private MediaView mediaView;

    @FXML
    private StackPane rootPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playMedia();
    }

    private void playMedia() {
        // Load the video
        String videoPath = getClass().getResource("/game/rise_of_valor_server/assets/Videos/InceptionCoderLogo.mp4").toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        // Fade in the video
        FadeTransition fadeInVideo = new FadeTransition(Duration.seconds(0.5), mediaView);
        fadeInVideo.setFromValue(0);
        fadeInVideo.setToValue(1);

        // Fade out the video
        FadeTransition fadeOutVideo = new FadeTransition(Duration.seconds(3), mediaView);
        fadeOutVideo.setFromValue(1);
        fadeOutVideo.setToValue(0);
        fadeOutVideo.setOnFinished(event -> {
            mediaPlayer.dispose();
            switchToNextScene();
        });

        // Play the video and apply fade transitions
        mediaPlayer.setOnReady(() -> {
            fadeInVideo.play();
            fadeInVideo.setOnFinished(e -> {
                mediaPlayer.play();
                mediaPlayer.setOnEndOfMedia(() -> fadeOutVideo.play());
            });
        });
    }

    private void switchToNextScene() {

        // Transition to the next scene (e.g., loading screen or main game)

        try {
            rootPane.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/game/rise_of_valor_server/fxml/loading.fxml"));
            rootPane.getChildren().add(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
}


}