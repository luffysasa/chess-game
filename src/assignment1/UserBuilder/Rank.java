package assignment1.UserBuilder;

public class Rank {
    private int RankPoints;
    private String RankName;

    public Rank(){
        this.RankPoints = 1000;
        this.RankName = "Silver";
    }

    public Rank(Integer RankPoints, String RankName){
        this.RankPoints = RankPoints;
        this.RankName = RankName;
    }

    public int getRankPoints() {
        return RankPoints;
    }

    public void addRankPoints() {
        RankPoints += 500;
        setRankName();
    }

    public void minusRankPoints() {
        RankPoints -= 500;
        setRankName();
    }

    public String getRankName() {
        return RankName;
    }

    public void setRankName() {
        if(RankPoints <= 1000){
            RankName = "Silver";
        }
        else if(RankPoints > 1000 && RankPoints <= 2000) {
            RankName = "Gold";
        }
        else if(RankPoints > 2000) {
            RankName = "Master";
        }
    }

    public String toString(){
        return String.format("%s-%s", RankName, RankPoints);
    }
}
