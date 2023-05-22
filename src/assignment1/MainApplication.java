package assignment1;

import assignment1.AllViews.View;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApplication extends Application {
    View view;
    ThreeMusketeers threeMusketeers;

    @Override
    public void start(Stage stage) throws Exception{
        this.threeMusketeers = new ThreeMusketeers();
        this.view = new View(this.threeMusketeers, stage);
    }

    public static void main(String[] args){
    	String filepath = "nights.wav";
        assignment1.Sound.BackgroundMusic musicObject = new assignment1.Sound.BackgroundMusic();
        musicObject.playMusic(filepath);
        
        launch();
    }
}
