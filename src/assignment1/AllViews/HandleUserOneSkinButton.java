package assignment1.AllViews;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class HandleUserOneSkinButton implements EventHandler<ActionEvent> {
    View view;

    public HandleUserOneSkinButton(View view){
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if(button.getText().equals("Skin")){
            this.view.showUserOneSkins();
        }
    }
}
