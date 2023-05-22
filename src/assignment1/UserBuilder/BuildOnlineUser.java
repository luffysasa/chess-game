package assignment1.UserBuilder;

public class BuildOnlineUser {

    public BuildOnlineUser(
            User user,
            String UserID,
            boolean isVisiable,
            int GoldenCoin,
            Rank rank,
            MailBox mailBox,
            WinningInfo winInfo,
            Skins skins){
        user.setUser(UserID, isVisiable, GoldenCoin, rank, mailBox, winInfo, skins);
    }


}