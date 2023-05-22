package assignment1.AllViews;

import assignment1.*;




import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import java.awt.Desktop;
import java.io.File;

import assignment1.RankingSystem.DoubleLink;
import assignment1.RankingSystem.getRank;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class HandleMainMenu implements EventHandler<ActionEvent> {
    private final View view;

    public HandleMainMenu(View view) {
        this.view = view;

    }


    @Override
    public void handle(ActionEvent actionEvent) {
        Button MainMenuButton = (Button) actionEvent.getSource();
        Button button = (Button) actionEvent.getTarget();

        for (Piece.Type pieceType : Piece.Type.values()) {
            if (pieceType.getType().equals(button.getText())) {
                view.setside(pieceType);
            }

            if (MainMenuButton.getText().equals("Human VS Human")) {
                this.view.showUserInputMenu();

            } else if (MainMenuButton.getText().equals("Rules")) {

                if (Desktop.isDesktopSupported()) {
                    try {
                        File rules = new File("ThreeMusketeersRules.pdf");
                        Desktop.getDesktop().open(rules);
                    } catch (Exception e) {
                        System.out.println("Can't find anything to open PDF. ALternativly the file can be foud in the root folder for this assignment");
                    }
                }
            } else if (MainMenuButton.getText().equals("LeaderBoard")) {
                this.view.showleaderBoard();
            } else {
                this.view.showVisitorBoard();

            }

            if (MainMenuButton.getText().equals("Human VS Human")) {
                this.view.showUserInputMenu();

            } else if (MainMenuButton.getText().equals("Rules")) {

                if (Desktop.isDesktopSupported()) {
                    try {
                        File rules = new File("ThreeMusketeersRules.pdf");
                        Desktop.getDesktop().open(rules);
                    } catch (Exception e) {
                        System.out.println("Can't find anything to open PDF. ALternativly the file can be foud in the root folder for this assignment");
                    }
                }
            } else {
                this.view.showVisitorBoard();
            }

        }

    }
}
