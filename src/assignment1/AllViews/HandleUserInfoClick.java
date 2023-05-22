package assignment1.AllViews;

import assignment1.ThreeMusketeers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class HandleUserInfoClick implements EventHandler<ActionEvent> {
    View view;
    ThreeMusketeers threeMusketeers;

    public HandleUserInfoClick(View view, ThreeMusketeers threeMusketeers){
        this.view = view;
        this.threeMusketeers = threeMusketeers;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if(button.getText().equals(this.threeMusketeers.getUserOne().toString())) {
            this.view.showUserOneInfo();
        }
        else if(button.getText().equals(this.threeMusketeers.getUserTwo().toString())){
            this.view.showUserTwoInfo();
        }
    }
}
