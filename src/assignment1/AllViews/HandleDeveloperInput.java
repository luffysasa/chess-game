package assignment1.AllViews;

import assignment1.ThreeMusketeers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HandleDeveloperInput implements EventHandler<ActionEvent> {
    ThreeMusketeers threeMusketeers;

    public HandleDeveloperInput(ThreeMusketeers threeMusketeers){
        this.threeMusketeers = threeMusketeers;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        VBox vBox = (VBox) button.getParent();
        TextField textField = (TextField) vBox.getChildren().get(1);
        this.threeMusketeers.getPublisher().sendMessage(textField.getText());
        System.out.println(this.threeMusketeers.getUserOne().saveInfo());
        System.out.println(this.threeMusketeers.getUserTwo().saveInfo());
    }
}
