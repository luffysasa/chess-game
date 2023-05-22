package assignment1.UserBuilder;

import assignment1.Announcement.Observer;
import assignment1.Board;
import assignment1.Usergroup;
import assignment1.Uservisit;

public class User implements Observer {
    private Board board;
    private String UserID;
    private String side;
    public int GoldenCoin;
    private Rank rank;
    private MailBox mail;
    private Skins skins;
    private WinningInfo winInfo;
    private boolean isVisiable;
    private Usergroup usergroup;

    public String getUserID(){
        return UserID;
    }

    public User(String UserID, String side, Board board){
        this.board = board;
        this.side = side;
        this.UserID = UserID;
        this.GoldenCoin = 0;
        this.isVisiable = true;
        this.rank = new Rank();
        this.mail = new MailBox();
        this.skins = new Skins();
        this.winInfo = new WinningInfo();
    }

    public User(String UserID){
        this.UserID = UserID;
    }

    public MailBox getMail(){
        return this.mail;
    }

    public Skins getSkins(){
        return this.skins;
    }

    public void changeUserVisiable(){
        isVisiable = !isVisiable;
    }

    private void saveuser(){
        usergroup.storeuser(this);
    }
    

    public void setUserWinInfo() {
        if (board.getWinner() != null) {
            if (board.getWinner().getType().equals(this.side)) {
                this.winInfo.addWin();
                this.rank.addRankPoints();
            }
            else{
                this.winInfo.addlost();
                this.rank.minusRankPoints();
            }
        }
    }

    public void setUser(String UserID,
                        boolean IsVisiable,
                        int GoldenCoin,
                        Rank rank,
                        MailBox mailBox,
                        WinningInfo winInfo,
                        Skins skins){
        this.UserID = UserID;
        this.isVisiable = IsVisiable;
        this.GoldenCoin = GoldenCoin;
        this.rank = rank;
        this.mail = mailBox;
        this.winInfo = winInfo;
        this.skins = skins;
    }

    public String toString(){
        if(this.isVisiable) {
            String userInfo = "UserID: " + UserID + "\n"
                    + "side: " + side + "\n"
                    + "Rank: " + rank.toString();
            return userInfo;
        }
        return "UserID: " + this.UserID;
    }

    public String saveInfo(){
        String saveUserInfo = "UserID: " + UserID + "\n"
                            + "IsVisiable: " + isVisiable + "\n"
                            + "GoldenCoin: " + GoldenCoin + "\n"
                            + "Rank: " + rank.toString() + "\n"
                            + "MailBox: " + mail.toString() + "\n"
                            + winInfo.toString() + "\n"
                            + skins.toString() + "\n";
        return saveUserInfo;
    }

    public String showUserInfo(){
        String userInfo =
                "UserID: " + UserID + "\n"
                + "IsVisiable: " + isVisiable + "\n"
                + "GoldenCoin: " + GoldenCoin + "\n"
                + "Rank: " + rank.toString() + "\n"
                + "Won - Lost: " + winInfo.getWin() + "-" + winInfo.getLose();
        return userInfo;
    }

    @Override
    public void updateMail(String newMessage) {
        this.mail.update(newMessage);
    }
}
