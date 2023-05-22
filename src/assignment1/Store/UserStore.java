package assignment1.Store;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserStore {
    private Map<String, Integer> store;
    public UserStore() throws FileNotFoundException {
        this.store = new HashMap<>();
        createStore();
    }

    public void createStore() throws FileNotFoundException {
        File storeInfo = new File("StoreList/storeInfo.txt");
        Scanner scanner = new Scanner(storeInfo);
        String[] fistSkinInfo = scanner.nextLine().split(":");
        this.store.put(fistSkinInfo[0], Integer.parseInt(fistSkinInfo[1]));
        while(scanner.hasNextLine()){
            String[] currentLineInfo = scanner.nextLine().split(":");
            this.store.put(currentLineInfo[0], Integer.parseInt(currentLineInfo[1]));
        }
    }

    public Map<String, Integer> getStore(){
        return this.store;
    }
}
