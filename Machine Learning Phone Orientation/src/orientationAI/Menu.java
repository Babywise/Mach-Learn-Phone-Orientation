package orientationAI;

import java.util.Scanner;

public class Menu {

	/**
	 * <h1>Description:</h1>
	 * <p>
	 * This method creates a divider in the console.
	 * </p>
	 * @since Feb. 8, 2022
	 * @version 1.0
	 */
	public static void newConsoleLine() {

		System.out.println("\n\n-------------------------------------------------------------------------\n\n");

	}

	/**
	 * <h1>Description:</h1>
	 * <p>
	 * This method displays a message and waits for input to continue the 
	 * program.
	 * </p>
	 * @since Feb. 8, 2022
	 * @version 1.0
	 */
	public static void enterToContinue() {
		
		System.out.print("\nPress Enter key to continue...");

		try {
			System.in.read();
		} catch (Exception e) {}
		
	}

	/**
	 * <h1>Description:</h1>
	 * <p>
	 * The Coordinate method retrieves data from the user and return a 
	 * Coordinate object.
	 * </p>
	 * @return
	 * @since Feb. 8, 2022
	 * @version 1.0
	 */
	public static Coordinate getUserCoordinate() {

		newConsoleLine();

		Scanner uInput = new Scanner(System.in);

		System.out.println("Please Enter A Coordinate (X, Y, Z):\n");

		System.out.print("x: ");
		double x = uInput.nextDouble();

		System.out.print("y: ");
		double y = uInput.nextDouble();

		System.out.print("z: ");
		double z = uInput.nextDouble();

		uInput.close();

		return new Coordinate(x, y, z);

	}

}
