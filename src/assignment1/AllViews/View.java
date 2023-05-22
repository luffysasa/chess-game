package assignment1.AllViews;


import assignment1.BoardPanel;
import assignment1.Piece;
import assignment1.SideView;

import assignment1.RankingSystem.DoubleLink;
import assignment1.RankingSystem.getRank;

import assignment1.ThreeMusketeers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.scene.shape.Circle;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class View {

    public ThreeMusketeers threeMusketeers;

    public TextField messageLable = new TextField("Choose a Cell you want to get a hint !!");

    BoardView boardView;


    Stage stage;
    Stage announcment;
    Stage userOneUI;
    Stage userTwoUI;
    Stage userOneMail;
    Stage userTwoMail;
    Stage userOneStore;
    Stage userTwoStore;
    Stage userOneSkin;
    Stage userTwoSkin;


    ThreeMusketeers.GameMode gameMode;
    ArrayList<Button> buttons;
    BoardPanel boardPanel;

    Stage rankingSystem;


    Stage GameOver;


    public View(ThreeMusketeers threeMusketeers, Stage stage){
        buttons = new ArrayList<Button>();



        this.threeMusketeers = threeMusketeers;


        this.stage = stage;
        this.stage.setTitle("Three Musketeers");
        this.stage.getIcons().add(new Image("file:MusketeerAndGuard/MusketeerAndGuard.jpg"));

        this.announcment = new Stage();
        this.announcment.setTitle("Publisher");

        this.userOneUI = new Stage();
        this.userOneUI.setTitle("User One Info");

        this.userTwoUI = new Stage();
        this.userTwoUI.setTitle("User Two Info");

        this.userOneMail = new Stage();
        this.userOneMail.setTitle("User One Mail");

        this.userTwoMail = new Stage();
        this.userTwoMail.setTitle("User Two Mail");

        this.userOneStore = new Stage();
        this.userOneStore.setTitle("Store");

        this.userTwoStore = new Stage();
        this.userTwoStore.setTitle("Store");

        this.userOneSkin = new Stage();
        this.userOneSkin.setTitle("Skin");

        this.userTwoSkin = new Stage();
        this.userTwoSkin.setTitle("Skin");
        
        this.GameOver = new Stage();
        this.GameOver.setTitle("Game Over");

        this.rankingSystem = new Stage();
        this.rankingSystem.setTitle("leaderboard");

        showMainMenu();

    }

    public void showUserInputMenu() {
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);

        TextField UserOneID = new TextField();
        TextField UserTwoID = new TextField();
        Button login = new Button("Login");
        login.setStyle(
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: linear-gradient(to right, cornflowerblue, cyan); " +
                "-fx-background-color: linear-gradient(to right, cornflowerblue, cyan), -fx-outer-border, -fx-inner-border; " +
                "-fx-background-radius: 100; " +
                "-fx-border-radius: 100; ");
        login.setOnAction(new HandleUserInput(this, this.threeMusketeers));
        Button back = new Button("Back");
        back.setStyle(
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: linear-gradient(to right, cornflowerblue, cyan); " +
                "-fx-background-color: linear-gradient(to right, cornflowerblue, cyan), -fx-outer-border, -fx-inner-border; " +
                "-fx-background-radius: 100; " +
                "-fx-border-radius: 100; ");
        back.setOnAction(new HandleUserInput(this, this.threeMusketeers));
        Label UserOneLabel = new Label("Input Musketeer Player ID");
        Label UserTwoLabel = new Label("Input Guard Player ID");
        UserOneLabel.setStyle("-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: lavender; ");
        UserTwoLabel.setStyle("-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: lavender; ");

        UserOneID.setMaxSize(200, 10);
        UserTwoID.setMaxSize(200, 10);

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(50, 100, 50, 100));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(UserOneLabel, UserOneID, UserTwoLabel, UserTwoID, login, back);

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 800, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void showMainMenu() {
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);

        Button humanVsHuman = new Button("Human VS Human");

        setHumanVSHumanStyle(humanVsHuman);

        Button leaderBoard = new Button("LeaderBoard");

        setLeaderboardStyle(leaderBoard);


        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(40, 100, 80, 100));
        vBox.setAlignment(Pos.TOP_CENTER);

        vBox.getChildren().add(humanVsHuman);

        vBox.getChildren().addAll(createModeButtons());
        vBox.getChildren().add(leaderBoard);



        borderPane.setCenter(vBox);
        Scene scene = new Scene(borderPane, 800, 500);
        stage.setScene(scene);
        stage.show();

    }


    public void runMove() {

        this.threeMusketeers.move(this.threeMusketeers.getCurrentAgent());
        this.boardPanel.updateCells();





    }


    private void showSideSelector(){
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().addAll(imageView);

        VBox vBox = new VBox(20,new SideView(this));
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);
        Scene scene = new Scene(borderPane, 800, 500);
        stage.setScene(scene);
        stage.show();

    }

    public void setLeaderboardStyle(Button button){
        button.setPrefHeight(50);
        button.setPrefWidth(200);
        button.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: linear-gradient(to right, blue, orange); " +
                        "-fx-background-color: linear-gradient(to right, blue, orange), -fx-outer-border, -fx-inner-border; " +
                        "-fx-background-radius: 100; " +
                        "-fx-border-radius: 100; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(106, 106, 106, 2), 6, 0.2, 3, 5)");
        button.setOnAction(new HandleMainMenu(this));
    }

    public void setHumanVSHumanStyle(Button button){
        button.setPrefHeight(50);
        button.setPrefWidth(200);
        button.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: linear-gradient(to right, cornflowerblue, cyan); " +
                        "-fx-background-color: linear-gradient(to right, cornflowerblue, cyan), -fx-outer-border, -fx-inner-border; " +
                        "-fx-background-radius: 100; " +
                        "-fx-border-radius: 100; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(106, 106, 106, 2), 6, 0.2, 3, 5)");
        button.setOnAction(new HandleMainMenu(this));
    }

    private ArrayList<Button> createModeButtons(){

        for (ThreeMusketeers.GameMode mode: ThreeMusketeers.GameMode.values()) {
            if (!(mode.getGameModeLabel().equals("Human vs Human"))){
                Button button = new Button(mode.getGameModeLabel());
                button.setPrefHeight(50);
                button.setPrefWidth(200);
                button.setStyle(
                        "-fx-font-size: 16px; " +
                                "-fx-font-weight: bold; " +
                                "-fx-text-fill: linear-gradient(to right, lime, coral); " +
                                "-fx-background-color: linear-gradient(to right, lime, coral), -fx-outer-border, -fx-inner-border; " +
                                "-fx-background-radius: 100; " +
                                "-fx-border-radius: 100; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(106, 106, 106, 2), 6, 0.2, 3, 5)");

                button.setOnAction(e -> this.setGameMode(mode));
                buttons.add(button);



            }

        }


        Button buttonR = new Button("Rules");
        buttonR.setPrefHeight(50);
        buttonR.setPrefWidth(200);
        buttonR.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: linear-gradient(to right, orange, red); " +
                        "-fx-background-color: linear-gradient(to right, orange, red), -fx-outer-border, -fx-inner-border; " +
                        "-fx-background-radius: 100; " +
                        "-fx-border-radius: 100; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(106, 106, 106, 2), 6, 0.2, 3, 5)");
        buttonR.setOnAction(new HandleMainMenu(this));
        buttons.add(buttonR);

        return buttons;


    }


    public void setLeaderBoardStyle(Button button){
        button.setPrefHeight(50);
        button.setPrefWidth(200);
        button.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: linear-gradient(to right, orange, red); " +
                        "-fx-background-color: linear-gradient(to right, orange, red), -fx-outer-border, -fx-inner-border; " +
                        "-fx-background-radius: 100; " +
                        "-fx-border-radius: 100; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(106, 106, 106, 2), 6, 0.2, 3, 5)");
        button.setOnAction(new HandleMainMenu(this));
    }

    public void LeaderBoard(){

        getRank r=new getRank();
        DoubleLink rank = r.getRankFromFile("UserList");
        rank.printList();
    }

    public void showleaderBoard(){

        getRank r=new getRank();
        DoubleLink rank = r.getRankFromFile("UserList");

        Image image = new Image("file:Background/leaderboard.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);

        TextArea rankingInfo = new TextArea();
        rankingInfo.setPrefHeight(100);

        rankingInfo.setText(rank.getString());

        VBox vBox = new VBox();
        vBox.setMaxSize(500, 400);
        vBox.setSpacing(50);
        vBox.setPadding(new Insets(5, 10, 10, 5));
        vBox.getChildren().addAll(rankingInfo);

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 1000, 1000);
        rankingSystem.setScene(scene);
        rankingSystem.show();
    }


    public void showBoard(){
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);

        Button userOneInfo = new Button(String.format("%s", this.threeMusketeers.getUserOne()));
        Button userTwoInfo = new Button(String.format("%s", this.threeMusketeers.getUserTwo()));
        userOneInfo.setOnAction(new HandleUserInfoClick(this, this.threeMusketeers));
        userTwoInfo.setOnAction(new HandleUserInfoClick(this, this.threeMusketeers));

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(40, 100, 80, 100));
        vBox.setAlignment(Pos.TOP_CENTER);

        BoardView boardView = new BoardView(this, this.threeMusketeers.getBoard(), this.threeMusketeers);
        boardView.setAlignment(Pos.CENTER);
        boardView.setAllCells();

        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(boardView);
        if(this.threeMusketeers.getUserOne() != null && this.threeMusketeers.getUserTwo() != null) {
            borderPane.setLeft(userOneInfo);
            borderPane.setRight(userTwoInfo);
        }
        Scene scene = new Scene(borderPane, 1000, 1000);
        stage.setScene(scene);
        stage.show();
    }


    public void shownothumanBoard(){
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);
        setboard();

        Button Hint = new Button("Hint");
        Hint.setOnAction(e -> this.boardPanel.hint());

        boardPanel.setAlignment(Pos.CENTER);
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(boardPanel);
        borderPane.setBottom(Hint);
        borderPane.setTop(messageLable);

        Scene scene = new Scene(borderPane, 1000, 1000);
        stage.setScene(scene);
        stage.show();
    }


    public void setboard(){
        BoardPanel boardpanel = new BoardPanel(this, this.threeMusketeers.getBoard());
        this.boardPanel = boardpanel;
    }

//    public void showVisitorBoard(){
//        Image image = new Image("file:Background/UserLogin.png");
//        ImageView imageView = new ImageView();
//        imageView.setImage(image);
//        BorderPane borderPane = new BorderPane();
//        borderPane.getChildren().add(imageView);
//
//
//        BoardView boardView = new BoardView(this, this.threeMusketeers.getBoard(), this.threeMusketeers);
//        boardView.setAlignment(Pos.CENTER);
//        boardView.setAllCells();
//
//        borderPane.setPadding(new Insets(20, 20, 20, 20));
//        borderPane.setCenter(boardView);
//        if(this.threeMusketeers.getUserOne() != null && this.threeMusketeers.getUserTwo() != null) {
//            borderPane.setLeft(userOneInfo);
//            borderPane.setRight(userTwoInfo);
//        }
//        Scene scene = new Scene(borderPane, 1000, 1000);
//        stage.setScene(scene);
//        stage.show();
//    }






    public void showVisitorBoard(){
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);

        BoardView boardView = new BoardView(this, this.threeMusketeers.getBoard());
        boardView.setAlignment(Pos.CENTER);

        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(boardView);
        Scene scene = new Scene(borderPane, 1000, 1000);
        stage.setScene(scene);
        stage.show();
    }






        public void showPublisher(){
        BorderPane borderPane = new BorderPane();

        Label inputLabel = new Label("Input message:");
        TextField message = new TextField();
        message.setMaxSize(200, 20);
        Button send = new Button("Send");
        send.setOnAction(new HandleDeveloperInput(this.threeMusketeers));

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(5, 10, 5, 10));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(inputLabel, message, send);

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 400, 100);
        announcment.setScene(scene);
        announcment.show();
    }

    public void showUserOneInfo(){
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);

        TextArea userOneInfo = new TextArea();
        userOneInfo.setPrefHeight(100);
        userOneInfo.setWrapText(true);
        userOneInfo.setText(this.threeMusketeers.getUserOne().showUserInfo());

        Button openMailBox = new Button("Mail");
        Button setSkin = new Button("Skin");
        Button store = new Button("Store");
        openMailBox.setOnAction(new HandleUserOneInfoButton(this));
        setSkin.setOnAction(new HandleUserOneSkinButton(this));
        store.setOnAction(new HandleUserOneInfoButton(this));

        VBox vBox = new VBox();
        vBox.setMaxSize(250, 250);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(5, 10, 10, 5));
        vBox.getChildren().addAll(userOneInfo, openMailBox, setSkin, store);

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 300, 300);
        userOneUI.setScene(scene);
        userOneUI.show();
    }

    public void showUserTwoInfo(){
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);

        TextArea userTwoInfo = new TextArea();
        userTwoInfo.setPrefHeight(100);
        userTwoInfo.setWrapText(true);
        userTwoInfo.setText(this.threeMusketeers.getUserTwo().showUserInfo());

        Button openMailBox = new Button("Mail");
        Button setSkin = new Button("Skin");
        Button store = new Button("Store");
        openMailBox.setOnAction(new HandleUserTwoInfoButton(this));
        setSkin.setOnAction(new HandleUserTwoSkinButton(this));
        store.setOnAction(new HandleUserTwoInfoButton(this));

        VBox vBox = new VBox();
        vBox.setMaxSize(250, 250);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(5, 10, 10, 5));
        vBox.getChildren().addAll(userTwoInfo, openMailBox, setSkin, store);

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 300, 300);
        userTwoUI.setScene(scene);
        userTwoUI.show();
    }

    public void showUserOneMail(){
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);

        ListView<String> mailList = new ListView<>();
        mailList.setEditable(false);
        mailList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        ObservableList<String> mailBoxList = FXCollections.observableArrayList();
        List<String> messageList = this.threeMusketeers.getUserOne().getMail().getMailBox();
        mailBoxList.addAll(messageList);
        mailList.setItems(mailBoxList);

        borderPane.setCenter(mailList);
        Scene scene = new Scene(borderPane);
        userOneMail.setScene(scene);
        userOneMail.show();

    }

    public void showUserTwoMail(){
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);

        ListView<String> mailList = new ListView<>();
        mailList.setEditable(false);
        mailList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        ObservableList<String> mailBoxList = FXCollections.observableArrayList();
        List<String> messageList = this.threeMusketeers.getUserTwo().getMail().getMailBox();
        mailBoxList.addAll(messageList);
        mailList.setItems(mailBoxList);

        borderPane.setCenter(mailList);
        Scene scene = new Scene(borderPane);
        userTwoMail.setScene(scene);
        userTwoMail.show();
    }

    public void showUserOneStore() throws FileNotFoundException {
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);

        VBox vBox = new VBox();
        vBox.setMaxSize(250, 250);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(5, 10, 10, 5));

        Map<String, Integer> storeMap = this.threeMusketeers.getUserStore().getStore();
        Set<String> storeKeySet = this.threeMusketeers.getUserStore().getStore().keySet();
        for(String key : storeKeySet){
            Button button = new Button();
            button.setText(String.format("Pay %s golden coin to buy skin %s", storeMap.get(key), key));
            button.setOnAction(new HandleUserOneStoreButton(this.threeMusketeers, this));
            vBox.getChildren().add(button);
        }

        borderPane.setCenter(vBox);
        Scene scene = new Scene(borderPane, 300, 300);
        userOneStore.setScene(scene);
        userOneStore.show();
    }

    public void showUserTwoStore() throws FileNotFoundException {
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);

        VBox vBox = new VBox();
        vBox.setMaxSize(250, 250);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(5, 10, 10, 5));

        Map<String, Integer> storeMap = this.threeMusketeers.getUserStore().getStore();
        Set<String> storeKeySet = this.threeMusketeers.getUserStore().getStore().keySet();
        for(String key : storeKeySet){
            Button button = new Button();
            button.setText(String.format("Pay %s golden coin to buy skin %s", storeMap.get(key), key));
            button.setOnAction(new HandleUserTwoStoreButton(this.threeMusketeers, this));
            vBox.getChildren().add(button);
        }

        borderPane.setCenter(vBox);
        Scene scene = new Scene(borderPane, 300, 300);
        userTwoStore.setScene(scene);
        userTwoStore.show();
    }

    public void showUserOneSkins(){
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);

        VBox vBox = new VBox();
        vBox.setMaxSize(250, 250);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(5, 10, 10, 5));

        ListView<String> userOneSkins = new ListView<>();
        userOneSkins.setEditable(false);
        userOneSkins.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(this.threeMusketeers.getUserOne().getSkins().getSkins());
        userOneSkins.setItems(observableList);

        Label label = new Label("Current Skin: " + this.threeMusketeers.getUserOne().getSkins().getCurrentSkin());
        Button button = new Button("Change to selected skin");
        Button back = new Button("Back to board");
        button.setOnAction(new HandleUserOneCurrentSkin(this, userOneSkins, label, this.threeMusketeers));
        back.setOnAction(new HandleUserOneCurrentSkin(this, userOneSkins, label, this.threeMusketeers));

        vBox.getChildren().addAll(label, userOneSkins, button, back);

        borderPane.setCenter(vBox);
        Scene scene = new Scene(borderPane, 300, 300);
        userOneSkin.setScene(scene);
        userOneSkin.show();
    }

    public void showUserTwoSkins(){
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);

        VBox vBox = new VBox();
        vBox.setMaxSize(250, 250);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(5, 10, 10, 5));

        ListView<String> userTwoSkins = new ListView<>();
        userTwoSkins.setEditable(false);
        userTwoSkins.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(this.threeMusketeers.getUserTwo().getSkins().getSkins());
        userTwoSkins.setItems(observableList);

        Label label = new Label("Current Skin: " + this.threeMusketeers.getUserTwo().getSkins().getCurrentSkin());
        Button button = new Button("Change to selected skin");
        Button back = new Button("Back to board");
        button.setOnAction(new HandleUserTwoCurrentSkin(this, userTwoSkins, label, this.threeMusketeers));
        back.setOnAction(new HandleUserTwoCurrentSkin(this, userTwoSkins, label, this.threeMusketeers));

        vBox.getChildren().addAll(label, userTwoSkins, button, back);

        borderPane.setCenter(vBox);
        Scene scene = new Scene(borderPane, 300, 300);
        userTwoSkin.setScene(scene);
        userTwoSkin.show();
    }


    public void setside(Piece.Type sidetype){
        threeMusketeers.selectMode(gameMode, sidetype);
        shownothumanBoard();
    }

    protected void setGameMode(ThreeMusketeers.GameMode mode) { // TODO
        gameMode = mode;
        showSideSelector();
    }
    protected void setmode(ThreeMusketeers.GameMode mode) { // TODO
        gameMode = mode;
        System.out.println(gameMode.getGameModeLabel());

    }




    
    public void showGameOver(Piece.Type winner){
        Image image = new Image("file:Background/UserLogin.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(imageView);
        
        if (winner == Piece.Type.MUSKETEER) {
        	Label label = new Label("GOOD JOB MUSKETEERS!!! YOU WON");
        	label.setFont(new Font("Verdana", 20));
        	label.setTextFill(Color.DARKRED);
        	borderPane.setCenter(label);
        } else {
        	Label label = new Label("WOOO GOOD JOB GUARDS!!! YOU WON");
        	label.setFont(new Font("Verdana", 20));
        	label.setTextFill(Color.DARKRED);
        	borderPane.setCenter(label);
        }

        Scene scene = new Scene(borderPane, 900, 100);
        GameOver.setScene(scene);
        GameOver.show();
    }

}
