package assignment1.UserBuilder;

import java.util.ArrayList;
import java.util.List;

public class Skins {
    List<String> skins;
    String currentSkin;

    public Skins(){
        this.currentSkin = "Default";
        this.skins = new ArrayList<>();
        this.skins.add("Default");
    }

    public Skins(String currentSkin, List<String> skins){
        this.currentSkin = currentSkin;
        this.skins = skins;
    }

    public List<String> getSkins(){
        return this.skins;
    }

    public String getCurrentSkin(){
        return this.currentSkin;
    }

    public void setSkins(String skinName){
        this.currentSkin = skinName;
    }

    public void addNewSkin(String newSkin){
        if(!skins.contains(newSkin)) {
            this.skins.add(newSkin);
        }
    }

    public boolean hasSkin(String newSkine){
        if(this.skins.contains(newSkine)){
            return true;
        }
        return false;
    }

    public String toString(){
        return String.format("CurrentSkin: %s\nSkins: %s", currentSkin, skins.toString().replace(", ", ","));
    }
}
