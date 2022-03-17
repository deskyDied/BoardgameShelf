import java.util.ArrayList;
import java.util.Random;

public class Boxes {
	
	private Random         rand;
	public  ArrayList<Box> storedBoxes;
	private int            size;
	private double         resolution;
	private double         maxHeight;
	private double         maxWidth;
	private Boolean        randomized;
	
	public Boxes(double maxHeight, double maxWidth, double resolution) {
		rand            = new Random();
		storedBoxes     = new ArrayList<Box>();
		this.size       = 0;
		this.resolution = resolution;
		this.maxHeight  = maxHeight;
		this.maxWidth   = maxWidth;
	}
	
	public Boxes(double maxHeight, double maxWidth, Boolean randomized, int size, double resolution) {
		this.rand        = new Random();
		this.storedBoxes = new ArrayList<Box>();
		this.size        = size;
		this.resolution  = resolution;
		this.maxHeight   = maxHeight;
		this.maxWidth    = maxWidth;
		this.randomized  = randomized;

		for(int i = 0; i<this.size; i++) {
			if(this.randomized) {
				double randHeight = rand.nextDouble()*this.maxHeight + 1;
				double randWidth  = rand.nextDouble()*this.maxWidth + 1;
				storedBoxes.add(new Box(randHeight, randWidth, this.resolution));
			}
			else {
				storedBoxes.add(new Box(this.maxHeight, this.maxWidth, this.resolution));
			}
		}
	}
	
	public void addBox(Box b) {
		storedBoxes.add(b);
	}
	
	public void addRandomBox() {
		double randHeight = rand.nextDouble()*this.maxHeight;
		double randWidth  = rand.nextDouble()*this.maxWidth;
		storedBoxes.add(new Box(randHeight, randWidth, resolution));
	}
	
	public void printBoxes() {
		int count = 0;
		for(Box box : storedBoxes) {
			System.out.printf("box %2d: %5.2f  x %5.2f\n", count, box.getHeight(), box.getWidth());
			for(double r = resolution; r<box.getHeight(); r+=resolution) {
				for(double c = resolution; c<box.getWidth(); c+=resolution) {
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
			System.out.printf("units: %d x %d\n\n" , box.getHeightUnits(), box.getWidthUnits());
			count++;
		}
	} 
	
	public int     getSize()       {return size;}
	public double  getResolution() {return resolution;}
	public double  getMaxHeight()  {return maxHeight;}
	public double  getMaxWidth()   {return maxWidth;}
	public Boolean getRandomized() {return randomized;}

}
