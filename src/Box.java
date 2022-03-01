
public class Box {
	private double height;
	private double width;
	public final char box = 'b';
	
	public Box(double height, double width) {
		this.height = height;
		this.width = width;
	}
	
	
	public void printBox() {
		for(int r = 0; r<height; r++) {
			for(int c = 0; c<width; c++) {
				System.out.printf("%c ", box);
			}
			System.out.printf("\n");
		}
	}
	
	
	/* Getters */
	public double getHeight() {return height;}
	public double getWidth() {return width;}
	public double getArea() {return height * width;}	
}
