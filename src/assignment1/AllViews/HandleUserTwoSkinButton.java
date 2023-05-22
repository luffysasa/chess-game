package assignment1.AllViews;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;

public class HandleUserTwoSkinButton implements EventHandler<ActionEvent> {
    View view;

    public HandleUserTwoSkinButton(View view){
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if(button.getText().equals("Skin")){
            this.view.showUserTwoSkins();
        }
    }
}
