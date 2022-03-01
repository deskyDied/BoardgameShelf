
public class Shelf {

	private int bsHeight;
	private int bsWidth;
	private double woodWidth;
	private int woodUnitWidth;
	private double resolution;
	private int divisionsPerInch;
	private char[][] grid;
	private int rows;
	private int cols;
	public final char wood = 'w';
	public final char space = ' ';
	
	public Shelf(int bsHeight, int bsWidth, double woodWidth, double resolution) {
		System.out.println("Creating Bookshelf: ");
		
		/* Calculate and assign values: */
		if((1/resolution)%1!=0) {
			System.out.println("System is not realistic!\n");
		}
		
		this.resolution = resolution;
		this.divisionsPerInch = (int) (1/resolution);
		this.bsHeight = bsHeight;
		this.bsWidth = bsWidth;
		this.rows = bsHeight * divisionsPerInch;
		this.cols = bsWidth * divisionsPerInch;
		grid = new char[rows][cols];
		System.out.printf("Res: %.2f, rows: %d, cols: %d, bsHeight: %d, bsWidth: %d, divPerInch: %d\n",
				 			resolution, rows, cols, bsHeight, bsWidth, divisionsPerInch);
		
		this.woodWidth = woodWidth;
		this.woodUnitWidth = (int) (woodWidth/resolution);
		
		System.out.printf("woodWidth: %.2f, woodUnitWidth: %d\n", woodWidth, woodUnitWidth); 
		System.out.println();
		
		/* Fill the bookshelf up with wood */
		for(int r = 0; r<rows; r++) {
			for(int c = 0; c<cols; c++) {
				grid[r][c] = wood;
			}
		}
		
		/* Empy out the middle */
		for(int r = woodUnitWidth; r<rows-woodUnitWidth; r++) {
			for(int c = woodUnitWidth; c<cols-woodUnitWidth; c++){
				grid[r][c] = space;
			}
		}
	}
	
	
	public void printShelf() {
		System.out.println();
		for(int r = 0; r<rows; r++) {
			for(int c = 0; c<cols; c++) {
				System.out.printf("%c ", grid[r][c]);
			}
			System.out.println();
		}

	}
	
	
	/* Getters */
	public double getResolution() {return resolution;}
	public int getHeight() {return bsHeight;}
	public int getWidth() {return bsWidth;}
	public double getWoodWidth() {return woodWidth;}

}
