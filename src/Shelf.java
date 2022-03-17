
public class Shelf {

	public  Boolean  empty;
	private int      bsHeight;
	private int      bsWidth;
	private double   woodWidth;
	private int      woodWidthUnits;
	private double   resolution;
	private int      unitsPerInch;
	public  char[][] grid; //TODO: potentially make private?
	private int      rows;
	private int      cols;
	
	public  final char wood = 'w';
	public  final char space = ' ';
	
	//Assumption that bsHeight and bsWidth will always be even integers
	public Shelf(int bsHeight, int bsWidth, double woodWidth, double resolution) {
		if((1/resolution)%1!=0) {
			System.out.println("System is not realistic!\n");
		}
		
		/* Calculate and assign values: */
		this.resolution     = resolution;
		this.unitsPerInch   = (int) (1/resolution);
		this.bsHeight       = bsHeight;
		this.bsWidth        = bsWidth;
		this.rows           = bsHeight * unitsPerInch;
		this.cols           = bsWidth * unitsPerInch;
		this.grid           = new char[rows][cols];
		this.woodWidth      = woodWidth;
		this.woodWidthUnits = (int) (woodWidth/resolution);
		this.empty          = true;
		
		/* Fill the bookshelf up with wood */
		for(int r = 0; r<rows; r++) {
			for(int c = 0; c<cols; c++) {
				grid[r][c] = wood;
			}
		}
		
		/* Empy out the middle */
		for(int r = woodWidthUnits; r<rows-woodWidthUnits; r++) {
			for(int c = woodWidthUnits; c<cols-woodWidthUnits; c++){
				grid[r][c] = space;
			}
		}
	}
	
	
	public void print() {
		System.out.println();
		for(int r = 0; r<rows; r++) {
			for(int c = 0; c<cols; c++) {
				System.out.printf("%c ", grid[r][c]);
			}
			System.out.println();
		}

	}
	
	public void clear() {
		for(int r = woodWidthUnits; r<rows-woodWidthUnits; r++) {
			for(int c = woodWidthUnits; c<cols-woodWidthUnits; c++){
				grid[r][c] = space;
			}
		}
	}
	
	
	/* Getters */
	public double getResolution()     {return resolution;}
	public int    getHeight()         {return bsHeight;}
	public int    getWidth()          {return bsWidth;}
	public int    getRows()           {return rows;}
	public int    getCols()           {return cols;}
	public double getWoodWidth()      {return woodWidth;}
	public int    getWoodWidthUnits() {return woodWidthUnits;}

}
