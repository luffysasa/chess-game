package assignment1;

public class SingletonVisitor {
	private static SingletonVisitor object;
	
	int GoldenCoin;
	String UserID;
	
	private SingletonVisitor(String UserID, int GoldenCoin){
		this.UserID = UserID;
		this.GoldenCoin = GoldenCoin;
		
	}
	
	public static SingletonVisitor getVisitor() {
		if (object == null) {
			object = new SingletonVisitor("Visitor",0);
		}
		return object;
	}

}
