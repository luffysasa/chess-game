package assignment1.AllViews;

import assignment1.ThreeMusketeers;

import assignment1.Uservisit;
import assignment1.ableUser;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.io.IOException;

public class HandleUserOneStoreButton implements EventHandler<ActionEvent> {
    ThreeMusketeers threeMusketeers;
    View view;


    public HandleUserOneStoreButton(ThreeMusketeers threeMusketeers, View view) {
        this.threeMusketeers = threeMusketeers;
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        String[] buttonInfo = button.getText().split(" ");
        int paidGoldenCoin = Integer.parseInt(buttonInfo[1]);
        String skinInfo = buttonInfo[buttonInfo.length - 1];

        Uservisit userone = new Uservisit();
        ableUser user = new ableUser(this.threeMusketeers.getUserOne().getUserID());
        if (userone.accept(user)) {
            if (this.threeMusketeers.getUserOne().GoldenCoin >= paidGoldenCoin &&
                    !this.threeMusketeers.getUserOne().getSkins().hasSkin(skinInfo)) {
                this.threeMusketeers.getUserOne().GoldenCoin -= paidGoldenCoin;
                this.threeMusketeers.getUserOne().getSkins().addNewSkin(skinInfo);
                try {
                    this.threeMusketeers.saveUser(this.threeMusketeers.getUserOne());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.view.showUserOneInfo();
            }

            if (this.threeMusketeers.getUserOne().GoldenCoin >= paidGoldenCoin &&
                    !this.threeMusketeers.getUserOne().getSkins().hasSkin(skinInfo)) {
                this.threeMusketeers.getUserOne().GoldenCoin -= paidGoldenCoin;
                this.threeMusketeers.getUserOne().getSkins().addNewSkin(skinInfo);
                try {
                    this.threeMusketeers.saveUser(this.threeMusketeers.getUserOne());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.view.showUserOneInfo();
            }

        }
    }
}
