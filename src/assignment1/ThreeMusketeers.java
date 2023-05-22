package assignment1;

import assignment1.Announcement.Publisher;
import assignment1.Store.UserStore;
import assignment1.UserBuilder.*;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ThreeMusketeers {

    private final Board board;
    private Agent musketeerAgent, guardAgent;
    private final Scanner scanner = new Scanner(System.in);
    private final List<Move> moves = new ArrayList<>();
    private User userOne;
    private User userTwo;
    private UserStore userStore;
    private Publisher publisher = new Publisher();
    Board boardCopy;
    BoardEvaluatorImpl boardEvaluator;

    // All possible game modes
    public enum GameMode {
        Human("Human vs Human"),
        HumanRandom("Human vs Computer (Random)"),
        HumanGreedy("Human vs Computer (Greedy)");

        private final String gameMode;
        public String getGameModeLabel() {
            return gameMode;
        }
        GameMode(final String gameMode) {
            this.gameMode = gameMode;
        }
    }

    public UserStore getUserStore(){
        return this.userStore;
    }

    public Publisher getPublisher(){
        return this.publisher;
    }

    public User getUserOne(){
        return userOne;
    }

    public User getUserTwo(){
        return userTwo;
    }

    public void setUserOne(User userOne){
        this.userOne = userOne;
    }

    public void setUserTwo(User userTwo){
        this.userTwo = userTwo;
    }

    public Board getBoard(){
        return board;
    }
    /**
     * Default constructor to load Starter board
     */
    public ThreeMusketeers() {
        this.board = new Board();
        creatingUserStore();
    }

    /**
     * Constructor to load custom board
     * @param boardFilePath filepath of custom board
     */
    public ThreeMusketeers(String boardFilePath) {
        this.board = new Board(boardFilePath);
        creatingUserStore();
    }

    /**
     * Play game with human input mode selector
     */
//    public void play() throws IOException {
//        System.out.println("Welcome! \n");
//        final GameMode mode = getModeInput();
//        System.out.println("Playing " + mode.gameMode);
//        play(mode);
//    }
//
//    /**
//     * Play game without human input mode selector
//     * @param mode the GameMode to run
//     */
//    public void play(GameMode mode){
//        selectMode(mode);
//        runGame();
//    }

    /**
     * Mode selector sets the correct agents based on the given GameMode
     * @param mode the selected GameMode
     */
    public void selectMode(GameMode mode, Piece.Type sideType) {
        switch (mode) {
            case Human -> {
                musketeerAgent = new HumanAgent(board);
                guardAgent = new HumanAgent(board);
            }
            case HumanRandom -> {
                // The following statement may look weird, but it's what is known as a ternary statement.
                // Essentially, it sets musketeerAgent equal to a new HumanAgent if the value M is entered,
                // Otherwise, it sets musketeerAgent equal to a new RandomAgent
                musketeerAgent = sideType.equals(Piece.Type.MUSKETEER) ? new HumanAgent(board) : new RandomAgent(board);
                guardAgent = sideType.equals(Piece.Type.GUARD) ? new HumanAgent(board) : new RandomAgent(board);
            }
            case HumanGreedy -> {
                musketeerAgent = sideType.equals(Piece.Type.MUSKETEER) ? new HumanAgent(board) : new GreedyAgent(board);
                guardAgent = sideType.equals(Piece.Type.GUARD) ? new HumanAgent(board) : new GreedyAgent(board);
            }
        }
    }

    /**
     * Runs the game, handling human input for move actions
     * Handles moves for different agents based on current turn 
     */
    private void runGame() {
        while(!board.isGameOver()) {
            System.out.println("\n" + board);

            final Agent currentAgent;
            if (board.getTurn() == Piece.Type.MUSKETEER)
                currentAgent = musketeerAgent;
            else
                currentAgent = guardAgent;

            if (currentAgent instanceof HumanAgent) // Human move
                switch (getInputOption()) {
                    case "M":
                        move(currentAgent);
                        break;
                    case "U":
                        if (moves.size() == 0) {
                            System.out.println("No moves to undo.");
                            continue;
                        }
                        else if (moves.size() == 1 || isHumansPlaying()) {
                            undoMove();
                        }
                        else {
                            undoMove();
                            undoMove();
                        }
                        break;
                    case "S":
                        board.saveBoard();
                        break;
                }
            else { // Computer move
                System.out.printf("[%s] Calculating move...\n", currentAgent.getClass().getSimpleName());
                move(currentAgent);
            }
        }

        System.out.println(board);
        System.out.printf("\n%s won!%n", board.getWinner().getType());
    }

    /**
     * Gets a move from the given agent, adds a copy of the move using the copy constructor to the moves stack, and
     * does the move on the board.
     * @param agent Agent to get the move from.
     */
    public void move(final Agent agent) {
            final Move move = agent.getMove();
            moves.add(new Move(move));
            board.move(move);
        if (move.fromCell.hasPiece()) {
            move.fromCell.setGraphic(new ImageView(move.fromCell.getPiece().getImage()));
        } else {
            move.fromCell.setGraphic(new Circle(50));
        }
        if (move.toCell.hasPiece()) {
            move.toCell.setGraphic(new ImageView(move.toCell.getPiece().getImage()));
        } else {
            move.toCell.setGraphic(new Circle(50));
        }
        
        String filepath = "piecemove.wav";
        assignment1.Sound.ClickSound musicObject = new assignment1.Sound.ClickSound();
        musicObject.playClickSound(filepath);
    }

    public void move(Move move) {
        moves.add(new Move(move));
        board.move(move);

        String filepath = "piecemove.wav";
        assignment1.Sound.ClickSound musicObject = new assignment1.Sound.ClickSound();
        musicObject.playClickSound(filepath);


    }

    /**
     * Removes a move from the top of the moves stack and undoes the move on the board.
     */
    private void undoMove() {
        board.undoMove(moves.remove(moves.size() - 1));
        System.out.println("Undid the previous move.");
    }

    /**
     * Get human input for move action
     * @return the selected move action, 'M': move, 'U': undo, and 'S': save
     */
    private String getInputOption() {
        System.out.printf("[%s] Enter 'M' to move, 'U' to undo, and 'S' to save: ", board.getTurn().getType());
        while (!scanner.hasNext("[MUSmus]")) {
            System.out.print("Invalid option. Enter 'M', 'U', or 'S': ");
            scanner.next();
        }
        return scanner.next().toUpperCase();
    }

    /**
     * Returns whether both sides are human players
     * @return True if both sides are Human, False if one of the sides is a computer
     */

    public Cell gethint(Cell fromcell){
        Cell suggestion = board.hints(fromcell,board);
        //need UI
        return suggestion;
    }

    private boolean isHumansPlaying() {
        return musketeerAgent instanceof HumanAgent && guardAgent instanceof HumanAgent;
    }

    /**
     * Get human input for side selection
     * @return the selected Human side for Human vs Computer games,  'M': Musketeer, G': Guard
     */
    private String getSideInput() {
        System.out.print("Enter 'M' to be a Musketeer or 'G' to be a Guard: ");
        while (!scanner.hasNext("[MGmg]")) {
            System.out.println("Invalid option. Enter 'M' or 'G': ");
            scanner.next();
        }
        return scanner.next().toUpperCase();
    }

    public Agent getCurrentAgent() {
        if (board.getTurn() == Piece.Type.MUSKETEER)
            return musketeerAgent;
        return guardAgent;
    }

    /**
     * Get human input for selecting the game mode
     * @return the chosen GameMode
     */
    private GameMode getModeInput() throws IOException {
        System.out.println("""
                    0: Human vs Human
                    1: Human vs Computer (Random)
                    2: Human vs Computer (Greedy)""");
        System.out.println("Choose a game mode to play i.e. enter a number: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid option. Enter 0, 1, or 2: ");
            scanner.next();
        }
        final int mode = scanner.nextInt();
        if (mode < 0 || mode > 2) {
            System.out.println("Invalid option.");
            return getModeInput();
        }
        return GameMode.values()[mode];
    }

//    public static void main(String[] args) throws IOException {
//        String boardFileName = "Boards/Starter.txt";
//        ThreeMusketeers game = new ThreeMusketeers(boardFileName);
//        game.play();
//    }

    public void saveUser(User user) throws IOException {
        File file = new File(String.format("UserList/%s.txt", user.getUserID()));
        try {
            file.createNewFile();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            String userInfo = user.saveInfo();
            writer.write(userInfo);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadOrSaveUser(User user) throws IOException {
        File file = new File("UserList");
        File[] allUsers = file.listFiles();
        assert allUsers != null;
        for(File eachUser : allUsers) {
            int startIndex = eachUser.toString().indexOf("\\") + 1;
            int endIndex = eachUser.toString().indexOf(".txt");
            String Username = eachUser.toString().substring(startIndex, endIndex);
            if (user.getUserID().equals(Username)) {
                loadUser(user);
                return;
            }
        }
        saveUser(user);
    }

    public void loadUser(User user) throws FileNotFoundException {
        File currentUser = new File(String.format("UserList/%s.txt", user.getUserID()));
        Scanner scanner = new Scanner(currentUser);
        String notDownUserID = scanner.nextLine();

        String notDownIsVisiable = scanner.nextLine();
        String isVisiableString = notDownIsVisiable.substring(notDownIsVisiable.indexOf(": ") + 2);

        String notDownCoin = scanner.nextLine();

        String notDownRank = scanner.nextLine();
        int RankPoints = Integer.parseInt(notDownRank.substring(notDownRank.indexOf("-") + 1));
        String RankName = notDownRank.substring(notDownRank.indexOf(": ") + 2,
                notDownRank.indexOf("-"));

        String notDownMailBox = scanner.nextLine();
        String[] MailBoxArray = notDownMailBox.substring(notDownMailBox.indexOf("[") + 1,
                notDownMailBox.indexOf("]")).split(",");
        List<String> mailBoxList = new ArrayList<>(Arrays.asList(MailBoxArray));

        String notDownWin = scanner.nextLine();
        String notDownLose = scanner.nextLine();
        int win = Integer.parseInt(notDownWin.substring(notDownWin.indexOf(": ") + 2));
        int lose = Integer.parseInt(notDownLose.substring(notDownLose.indexOf(": ") + 2));

        String notDownCurrentSkin = scanner.nextLine();
        String notDownSkins = scanner.nextLine();
        String currentSkin = notDownCurrentSkin.substring(notDownCurrentSkin.indexOf(": ") + 2);
        String[] SkinsArray = notDownSkins.substring(notDownSkins.indexOf("[") + 1,
                notDownSkins.indexOf("]")).split(",");
        List<String> SkinsList = new ArrayList<>(Arrays.asList(SkinsArray));

        String UserID = notDownUserID.substring(notDownUserID.indexOf(": ") + 2);
        boolean isVisiable = !isVisiableString.equals("false");
        int GoldenCoin = Integer.parseInt(notDownCoin.substring(notDownCoin.indexOf(": ") + 2));
        Rank rank = new Rank(RankPoints, RankName);
        MailBox mailBox = new MailBox(mailBoxList);
        WinningInfo winInfo = new WinningInfo(win, lose);
        Skins skins = new Skins(currentSkin, SkinsList);

        new BuildOnlineUser(user, UserID, isVisiable, GoldenCoin, rank, mailBox, winInfo, skins);
    }

    public void creatingUserStore(){
        try {
            this.userStore = new UserStore();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
