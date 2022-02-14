package orientationAI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ClassifierAI {

	NearestNeighbourClassifier NN = new NearestNeighbourClassifier();
	AnotherClassifier AC = new AnotherClassifier();

	/**
	 * <h1>Description:</h1>
	 * <p>
	 * The train method reads the TrainingData.txt file and parses each line into a
	 * coordinate object. The coordinates are then added to an arraylist.
	 * </p>
	 * 
	 * @param trainArray
	 * @since Feb. 8, 2022
	 * @version 1.0
	 */
	public void train(ArrayList<Coordinate> trainArray) {

		// trainingData Source File
		File file = new File("src/orientationAI/resources/trainingData.txt");

		BufferedReader bReader = null;

		try {
			bReader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// Output error to console
			e.printStackTrace();
		}

		String currLine;

		try {
			// Parse data from file into Coordinate Object and add to ArrayList
			while ((currLine = bReader.readLine()) != null) {

				// Split into an array of substrings by comma
				String[] parsedValue = currLine.split(",");

				double x = Double.parseDouble(parsedValue[0]);
				double y = Double.parseDouble(parsedValue[1]);
				double z = Double.parseDouble(parsedValue[2]);
				int label = Integer.parseInt(parsedValue[3]);

				Coordinate currCoord = new Coordinate(x, y, z, label);
				trainArray.add(currCoord);

			}

			bReader.close();

		} catch (IOException e) {
			// Output error to console
			e.printStackTrace();
		}

	}

	/**
	 * <h1>Description:</h1>
	 * <p>
	 * The test method reads the testingData.txt file and parses each line into a
	 * coordinate object. The coordinates are then added to an arraylist and compared
	 * against the data found in trainArray.
	 * </p>
	 * @param trainArray
	 * @since Feb. 8, 2022
	 * @version 1.0
	 */
	public void test(ArrayList<Coordinate> trainArray) {

		// testingData Source File
		File file = new File("src/orientationAI/resources/testingData.txt");

		BufferedReader bReader = null;

		try {
			bReader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// Output error to console
			e.printStackTrace();
		}

		String status;
		String currLine;

		try {
			// Parse data from file into Coordinate Object and add to ArrayList
			while ((currLine = bReader.readLine()) != null) {

				// Split into an array of substrings by comma
				String[] parsedValue = currLine.split(",");

				double x = Double.parseDouble(parsedValue[0]);
				double y = Double.parseDouble(parsedValue[1]);
				double z = Double.parseDouble(parsedValue[2]);
				int label = Integer.parseInt(parsedValue[3]);

				Coordinate currCoord = new Coordinate(x, y, z);

				this.NN.setNN(currCoord, trainArray);

				if (currCoord.label == label) {
					status = "Passed";
				} else {
					status = "Failed";
				}

				System.out.println("x: " + currCoord.x + "\t" + "y: " + currCoord.y + "\t" + "z: " + currCoord.z + "\t"
						+ "Orientation: " + currCoord.label + "\t" + "|| " + status);

			}

			bReader.close();

		} catch (IOException e) {
			// Output error to console
			e.printStackTrace();
		}

	}

	/**
	 * <h1>Description:</h1>
	 * <p>
	 * The testCoordinate method takes a custom coordinate and compares
	 * against the data found in trainArray.
	 * </p>
	 * @param c
	 * @param trainArray
	 * @since Feb. 8, 2022
	 * @version 1.0
	 */
	public void testCoordinate(Coordinate c, ArrayList<Coordinate> trainArray) {

		this.NN.setNN(c, trainArray);
		System.out.println("\nx: " + c.x + "  " + "y: " + c.y + "  " + "z: " + c.z + "  " + "Orientation: " + c.label);

		Menu.enterToContinue();
		
		Menu.newConsoleLine();

		// Will print setAC function call
		this.AC.setAC(c, trainArray);

		Menu.newConsoleLine();

	}

	/**
	 * <h1>Description:</h1>
	 * <p>
	 * The checkUnknownFile method reads the unknownData.txt file and parses each line into a
	 * coordinate object. The coordinates are then added to an arraylist and compared
	 * against the data found in trainArray to determine the orientation of the point.
	 * Once all orientations have been determined the method writes the results to the original
	 * file.
	 * </p>
	 * @param trainArray
	 * @since Feb. 8, 2022
	 * @version 1.0
	 */
	public void checkUnknownFile(ArrayList<Coordinate> trainArray) {

		// unknownData Source File
		File file = new File("src/orientationAI/resources/unknownData.txt");
		ArrayList<Coordinate> unknownArray = new ArrayList<Coordinate>();

		BufferedReader bReader = null;

		try {
			bReader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// Output error to console
			e.printStackTrace();
		}

		String currLine;

		try {
			// Parse data from file into Coordinate Object and add to ArrayList
			while ((currLine = bReader.readLine()) != null) {

				// Split into an array of substrings by comma
				String[] parsedValue = currLine.split(",");

				double x = Double.parseDouble(parsedValue[0]);
				double y = Double.parseDouble(parsedValue[1]);
				double z = Double.parseDouble(parsedValue[2]);

				Coordinate currCoord = new Coordinate(x, y, z);

				this.NN.setNN(currCoord, trainArray);

				unknownArray.add(currCoord);

			}

			bReader.close();

		} catch (IOException e) {
			// Output error to console
			e.printStackTrace();
		}

		// Write results to unknownData
		
		// Open file, clear contents and close 'FileWriter'
		try {
			new FileWriter("src/orientationAI/resources/unknownData.txt", false).close();
		} catch (IOException e) {
			// Output error to console
			e.printStackTrace();
		}

		try {
			FileWriter myWriter = new FileWriter("src/orientationAI/resources/unknownData.txt");
			
			// Write coordinate to file
			for (int i = 0; i < unknownArray.size(); i++ ) {	
				
				String strCoord = Double.toString(unknownArray.get(i).x) + "," +
								  Double.toString(unknownArray.get(i).y) + "," +
								  Double.toString(unknownArray.get(i).z) + "," +
								  Integer.toString(unknownArray.get(i).label);
				
				if (i == unknownArray.size() - 1) {
					myWriter.write(strCoord);
				} else {
					myWriter.write(strCoord + "\n");
				}
				
			}
			
			myWriter.close();
			
		} catch (IOException e) {
			// Output error to console
			e.printStackTrace();
		}
		
		System.out.println("unknownData.txt calculated and filled");

	}

}
