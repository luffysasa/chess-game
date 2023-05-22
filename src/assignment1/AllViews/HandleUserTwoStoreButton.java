package assignment1.AllViews;

import assignment1.ThreeMusketeers;

import assignment1.Uservisit;
import assignment1.ableUser;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.io.IOException;

public class HandleUserTwoStoreButton implements EventHandler<ActionEvent> {
    ThreeMusketeers threeMusketeers;
    View view;

    public HandleUserTwoStoreButton(ThreeMusketeers threeMusketeers, View view){
        this.threeMusketeers = threeMusketeers;
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        String[] buttonInfo = button.getText().split(" ");
        int paidGoldenCoin = Integer.parseInt(buttonInfo[1]);
        String skinInfo = buttonInfo[buttonInfo.length - 1];


        Uservisit usertwo = new Uservisit();
        ableUser user = new ableUser(this.threeMusketeers.getUserTwo().getUserID());
        if(usertwo.accept(user)){
            if(this.threeMusketeers.getUserTwo().GoldenCoin >= paidGoldenCoin &&
                    !this.threeMusketeers.getUserTwo().getSkins().hasSkin(skinInfo)) {
                this.threeMusketeers.getUserTwo().GoldenCoin -= paidGoldenCoin;
                this.threeMusketeers.getUserTwo().getSkins().addNewSkin(skinInfo);
                try {
                    this.threeMusketeers.saveUser(this.threeMusketeers.getUserTwo());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.view.showUserTwoInfo();
            }
        }



        if(this.threeMusketeers.getUserTwo().GoldenCoin >= paidGoldenCoin &&
                !this.threeMusketeers.getUserTwo().getSkins().hasSkin(skinInfo)) {
            this.threeMusketeers.getUserTwo().GoldenCoin -= paidGoldenCoin;
            this.threeMusketeers.getUserTwo().getSkins().addNewSkin(skinInfo);
            try {
                this.threeMusketeers.saveUser(this.threeMusketeers.getUserTwo());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.view.showUserTwoInfo();
        }

    }
}
