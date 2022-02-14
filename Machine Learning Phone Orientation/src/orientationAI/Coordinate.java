package orientationAI;

public class Coordinate {
	
	double x;
	double y;
	double z;
	int label;
	
	public Coordinate() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Coordinate(double x, double y, double z, int label) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.label = label;
	}
	
	public void setCoordinate(int label) {
		this.label = label;
	}
	
}
