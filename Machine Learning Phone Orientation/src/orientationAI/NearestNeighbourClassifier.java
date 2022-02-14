package orientationAI;

import java.util.ArrayList;

public class NearestNeighbourClassifier {

	/**
	 * <h1>Description:</h1>
	 * <p>
	 * Finds the nearest neighbor and sets the orientation of the unknown coordinate
	 * </p>
	 * @param c
	 * @param trainingData
	 * @since Feb. 8, 2022
	 * @version 1.0
	 */
	public void setNN(Coordinate c, ArrayList<Coordinate> trainingData) {
		
		Coordinate cNearest = null;
		double NNDistance = 0;
		
		for (int i = 0; i < trainingData.size(); i++) {
			
			double coordX = c.x;
			double coordY = c.y;
			double coordZ = c.z;

			double nearX = trainingData.get(i).x;
			double nearY = trainingData.get(i).y;
			double nearZ = trainingData.get(i).z;

			// Formula to find distance between 2 points on a 3D Plane
			double length = Math.sqrt((Math.pow((coordX - nearX), 2) + 
					Math.pow((coordY - nearY), 2) + 
					Math.pow((coordZ - nearZ), 2)));
			
			// Finds nearest length
			if (length <= NNDistance) {
				
				cNearest = trainingData.get(i);
				NNDistance = length;
				
			} else if (i == 0) {
				NNDistance = length;
			}

		}

		if (cNearest == null) {
			System.out.println("Nearest point not found.");
			return;
		}
		
		// Set orientation to the NearestNeighbor's orientation
		c.setCoordinate(cNearest.label);
		
	}

}
