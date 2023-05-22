package assignment1.AllViews;

import assignment1.ThreeMusketeers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class HandleUserTwoCurrentSkin implements EventHandler<ActionEvent> {
    View view;
    ListView<String> listView;
    Label label;
    ThreeMusketeers threeMusketeers;

    public HandleUserTwoCurrentSkin(View view, ListView<String> listView, Label label, ThreeMusketeers threeMusketeers){
        this.view = view;
        this.listView = listView;
        this.label = label;
        this.threeMusketeers = threeMusketeers;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if(button.getText().equals("Change to selected skin")){
            this.threeMusketeers.getUserTwo().getSkins().setSkins(this.listView.getSelectionModel().getSelectedItem());
            this.label.setText("Current Skin: " + this.threeMusketeers.getUserTwo().getSkins().getCurrentSkin());
        }
        else if(button.getText().equals("Back to board")){
            this.view.showBoard();
        }
    }
}
