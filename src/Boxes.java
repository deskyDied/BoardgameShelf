import java.util.ArrayList;
import java.util.Random;

public class Boxes {
	
	private Random rand;
	ArrayList<Box> storedBoxes;
	int size;
	double height;
	double width;
	double resolution;
	Boolean randomized;
	
	
	public Boxes(int size, double height, double width, double resolution, Boolean randomized) {
		rand = new Random();
		storedBoxes = new ArrayList<Box>();
		this.size = size;
		this.height = resolution * (Math.round(height/resolution));
		this.width  = resolution * (Math.round(width /resolution));
		this.resolution = resolution;
		this.randomized = randomized;

		if(resolution<.01) {
			System.out.printf("YOUR BOXES ARE TOO SMALL");
			//TODO: Throw an exception? What's the appropriate way to handle this
		}
		for(int i = 0; i<size; i++) {
			if(randomized) {
				double floorHeight = resolution * Math.round((rand.nextDouble()*this.height + resolution)/resolution); 
				double floorWidth  = resolution * Math.round((rand.nextDouble()*this.width  + resolution)/resolution);
				storedBoxes.add(new Box(floorHeight, floorWidth));
			}
			else {
				storedBoxes.add(new Box(this.height, this.width));
			}
		}
	}
	
	public void printBoxes() {
		int count = 0;
		for(Box box : storedBoxes) {
			System.out.printf("box %2d: %5.2f  x %5.2f\n", count, box.getHeight(), box.getWidth());
			for(double r = 0; r<box.getHeight(); r+=this.resolution) {
				for(double c = 0; c<box.getWidth(); c+=this.resolution) {
					System.out.printf("%c ", box.boxChar);
				}
				System.out.printf("\n");
			}
			System.out.printf("\n");
			count++;
		}
	}

	public void printBoxesDimensions() {
		int count = 0;
		for(Box box : storedBoxes) {
			System.out.printf("box %2d: %5.2f  x %5.2f\n", count, box.getHeight(), box.getWidth());
			count++;
		}
	}
	

}
