
public class Box {
	private      double    height;
	private      int       heightUnits;
	private      double    width;
	private      int       widthUnits;
	private      double    resolution;
	public final char      boxChar = 'b';
	
	public Box(double height, double width, double resolution) {
		this.height     = height;
		this.width      = width;
		this.resolution = resolution;
		
		//int unitsPerInch = (int) (1/resolution);
		
		heightUnits = (int) Math.round(height/resolution);
		widthUnits  = (int) Math.round(width/resolution);
	}
	
	
	public void print() {
		for(double r = resolution; r<height; r+=resolution){
			for(double c = resolution; c<width; c+=resolution) {
				System.out.printf("%c ", boxChar);
			}
			System.out.printf("\n");
		}
	}
	
	public void printDimensions() 
	{
		System.out.printf("%.2fx%.2f\n%dx%d units", height, width, heightUnits, widthUnits);
	}
	
	/* Getters */
	public double getHeight()      {return height;}
	public double getWidth()       {return width;}
	public double getArea()        {return height * width;}	
	public int    getHeightUnits() {return heightUnits;}
	public int    getWidthUnits()  {return widthUnits;}
	public double getResolution()  {return resolution;}
}
