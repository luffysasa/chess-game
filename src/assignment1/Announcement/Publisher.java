package assignment1.Announcement;

import assignment1.UserBuilder.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Publisher extends Observable{
    List<User> users;

    public Publisher(){
        this.users = new ArrayList<>();
    }

    @Override
    public void register(User user) {
        if(!this.users.contains(user)) {
            this.users.add(user);
        }
    }

    @Override
    public void unregister(User user) {
        if(this.users.contains(user)) {
            this.users.remove(user);
        }
    }

    @Override
    public void sendMessage(String newMessage) {
        for(User user : this.users){
            user.updateMail(newMessage);
        }
        File file = new File("UserList");
        File[] fileArrays = file.listFiles();
        for(File eachFile : fileArrays){
            try {
                User user = loadFileToUser(eachFile);
                user.updateMail(newMessage);
                saveUser(user);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

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

    public User loadFileToUser(File file) throws FileNotFoundException {
        User user = new User(file.getName());
        Scanner scanner = new Scanner(file);
        String notDownUserID = scanner.nextLine();
        String UserID = notDownUserID.substring(notDownUserID.indexOf(": ") + 2);

        String notDownIsVisiable = scanner.nextLine();
        String isVisiableString = notDownIsVisiable.substring(notDownIsVisiable.indexOf(": ") + 2);
        boolean isVisiable = !isVisiableString.equals("false");

        String notDownCoin = scanner.nextLine();
        int GoldenCoin = Integer.parseInt(notDownCoin.substring(notDownCoin.indexOf(": ") + 2));

        String notDownRank = scanner.nextLine();
        int RankPoints = Integer.parseInt(notDownRank.substring(notDownRank.indexOf("-") + 1));
        String RankName = notDownRank.substring(notDownRank.indexOf(": ") + 2,
                notDownRank.indexOf("-"));
        Rank rank = new Rank(RankPoints, RankName);

        String notDownMailBox = scanner.nextLine();
        String[] MailBoxArray = notDownMailBox.substring(notDownMailBox.indexOf("[") + 1,
                notDownMailBox.indexOf("]")).split(",");
        List<String> mailBoxList = new ArrayList<>(Arrays.asList(MailBoxArray));
        MailBox mailBox = new MailBox(mailBoxList);

        String notDownWin = scanner.nextLine();
        String notDownLose = scanner.nextLine();
        int win = Integer.parseInt(notDownWin.substring(notDownWin.indexOf(": ") + 2));
        int lose = Integer.parseInt(notDownLose.substring(notDownLose.indexOf(": ") + 2));
        WinningInfo winInfo = new WinningInfo(win, lose);

        String notDownCurrentSkin = scanner.nextLine();
        String notDownSkins = scanner.nextLine();
        String currentSkin = notDownCurrentSkin.substring(notDownCurrentSkin.indexOf(": ") + 2);
        String[] SkinsArray = notDownSkins.substring(notDownSkins.indexOf("[") + 1,
                notDownSkins.indexOf("]")).split(",");
        List<String> SkinsList = new ArrayList<>(Arrays.asList(SkinsArray));
        Skins skins = new Skins(currentSkin, SkinsList);

        user.setUser(UserID, isVisiable, GoldenCoin, rank, mailBox, winInfo, skins);
        return user;
    }
}
