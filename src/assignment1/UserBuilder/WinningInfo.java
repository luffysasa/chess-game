package assignment1.UserBuilder;

public class WinningInfo {
    private int win;
    private int lose;

    public WinningInfo(){
        this.win = 0;
        this.lose = 0;
    }

    public WinningInfo(int win, int lose){
        this.win = win;
        this.lose = lose;
    }

    public int getWin(){
        return this.win;
    }

    public int getLose(){
        return this.lose;
    }

    public void addWin(){
        win += 1;
    }

    public void addlost(){
        lose += 1;
    }

    public String toString(){
        return String.format("Win: %s\nLose: %s", win, lose);
    }
}
