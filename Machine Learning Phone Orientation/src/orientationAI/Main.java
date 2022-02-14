package orientationAI;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Coordinate> coordArray = new ArrayList<Coordinate>();
		
		ClassifierAI c = new ClassifierAI();
	
		c.train(coordArray);
		c.test(coordArray);
		
		Menu.enterToContinue();
		
		c.testCoordinate(Menu.getUserCoordinate(), coordArray);
		
		c.checkUnknownFile(coordArray);
		
	}

}
