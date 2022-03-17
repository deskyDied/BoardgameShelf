import java.util.ArrayList;
import java.util.Random;

public class ShelfFiller {

	private int    boxPaddingUnits;
	public final char boxPaddingChar = '-';
	
	public ShelfFiller(int boxPaddingUnits ) {

		this.boxPaddingUnits = boxPaddingUnits;
	}
	
	
	//boxPaddingUnits is applied to both sides. 
	//A box with resolution .05 inches and padding of 2 units will have
	//.10 inches of space on both sides, .10 inches of space on the top
	//and 0 inches of space on the bottom
	public void fillShelfStandard(Shelf shelf, Boxes boxes, int boxPaddingUnits)
	{
		if(shelf.getResolution() != boxes.getResolution()) {
			System.out.println("resolution does not match, cannot fill bookshelf");
			return;
		}
		
		ArrayList<Box> leftoverBoxes = new ArrayList<Box>();
		
		this.boxPaddingUnits = boxPaddingUnits;
		
		int boxesPlaced = 0;
		for(Box box : boxes.storedBoxes) {
			Boolean boxWasPlaced = placeBoxFirstSpaceFound(shelf, box);
			if(boxWasPlaced) {boxesPlaced++;}
		}
		System.out.printf("Fill Shelf (Standard) completed, %d boxes placed", boxesPlaced);
	}

	public void fillShelfOptimizeRandom(Shelf shelf, Boxes boxes, int boxPaddingUnits, int attempts){
		if(shelf.getResolution() != boxes.getResolution()) {
			System.out.println("resolution does not match, cannot fill bookshelf");
			return;
		}
		
		
		this.boxPaddingUnits = boxPaddingUnits;
		
		//Randomly attempt to find the best order to fill the boxes in
		int[] bestOrder = intArrayOfIndexes(boxes.storedBoxes.size());
		int bestBoxesPlaced = 0;
		
		//TODO: possibly remove, this is just to sync with the method above
		for(int i = 0; i<bestOrder.length; i++) {
			Box currentBox = boxes.storedBoxes.get(bestOrder[i]);
			Boolean boxWasPlaced = placeBoxFirstSpaceFound(shelf, currentBox);
			if(boxWasPlaced) {bestBoxesPlaced++;}
		}
		
		for(int a = 0; a<attempts; a++) {
			shelf.clear();
			int boxesPlaced = 0;
			int[] currentOrder = new int[bestOrder.length];
			copyArray(bestOrder,currentOrder);
			shuffleArray(currentOrder);
			
			for(int i = 0; i<currentOrder.length; i++) {
				Box currentBox = boxes.storedBoxes.get(currentOrder[i]);
				Boolean boxWasPlaced = placeBoxFirstSpaceFound(shelf, currentBox);
				if(boxWasPlaced) {
					boxesPlaced++;
				}
			}
			
			if(boxesPlaced > bestBoxesPlaced) {
				copyArray(currentOrder,bestOrder);
				bestBoxesPlaced = boxesPlaced;
			}
		}
		
		//Now that we have the order fill the boxes
		shelf.clear();
		for(int i = 0; i<bestOrder.length; i++) {
			Box currentBox = boxes.storedBoxes.get(bestOrder[i]);
			placeBoxFirstSpaceFound(shelf, currentBox);
		}
		System.out.printf("Fill Shelf (Optimize Random) completed, %d boxes placed", bestBoxesPlaced);
	}
	
	//TODO: There's a problem with the width of the box border that's drawn
	public Boolean spaceForBox(Shelf shelf, Box b, int rowStart, int colStart) {
		Boolean space = true;
		
		//Out of bounds check
		if((rowStart + b.getHeightUnits() + boxPaddingUnits) > shelf.getRows()) {
			return false;
		}
		if((colStart + b.getWidthUnits() + boxPaddingUnits*2) > shelf.getCols()) {
			return false;
		}
		
		//Got to location and scan for space
		for(int r = rowStart; r < (rowStart + b.getHeightUnits()+(boxPaddingUnits)); r++) {
			for(int c = colStart; c < (colStart + b.getWidthUnits()+(boxPaddingUnits*2)); c++) {
				if(shelf.grid[r][c] != shelf.space) {
					space = false; 
				}
			}
		}
		
		return space;
	}
	
	public Boolean placeBoxFirstSpaceFound(Shelf shelf, Box box) {
		Boolean foundSpaceForBox = false;
		for(int r = 0; r<shelf.getRows(); r++) {
			for(int c = 0; c<shelf.getCols(); c++) {
				if(shelf.grid[r][c] == shelf.space){
					if(!foundSpaceForBox) {
						if(spaceForBox(shelf, box, r,c)) {
							foundSpaceForBox = true;
							placeBox(shelf, box, r, c);
							break;
						}
					}
				}
			}
			if(foundSpaceForBox) {break;}
		}
		
		return foundSpaceForBox;
	}
	
	public void placeBox(Shelf shelf, Box b, int rowStart, int colStart) {
		//place wood
		int rowBorderStart = rowStart - shelf.getWoodWidthUnits();
		int rowBorderEnd = rowStart + b.getHeightUnits() + boxPaddingUnits + shelf.getWoodWidthUnits();
		int colBorderStart = colStart - shelf.getWoodWidthUnits();
		int colBorderEnd = colStart + boxPaddingUnits + b.getWidthUnits() + boxPaddingUnits + shelf.getWoodWidthUnits();
		for(int r = rowBorderStart; r < rowBorderEnd; r++) {
			for(int c = colBorderStart; c < colBorderEnd; c++) {
				shelf.grid[r][c] = shelf.wood;
			}
		}
		
		//hollow out wood
		for(int r = rowStart; r < (rowStart + b.getHeightUnits() + boxPaddingUnits); r++) {
			for(int c = colStart; c < (colStart + b.getWidthUnits() + 2*boxPaddingUnits); c++) {
				shelf.grid[r][c] = boxPaddingChar;
			} 
		}
		
		//place box
		for(int r = rowStart + boxPaddingUnits; r < (rowStart + b.getHeightUnits() + boxPaddingUnits); r++) {
			for(int c = colStart+boxPaddingUnits; c < (colStart + b.getWidthUnits()+boxPaddingUnits); c++) {
				shelf.grid[r][c] = b.boxChar;
			} 
		}
				
	}

	public int[] intArrayOfIndexes(int size) {
		int[] num = new int[size];
		for(int i = 0; i<size; i++) {
			num[i] = i;
		}
		return num;
	}
	
	public void shuffleArray(int[] arrToShuffle) {
		Random rand = new Random();
		for(int i = 0; i<arrToShuffle.length; i++) 
		{
			int index = rand.nextInt(arrToShuffle.length);
			int temp = arrToShuffle[index];
			arrToShuffle[index] = arrToShuffle[i];
			arrToShuffle[i] = temp;
		}
	}

	public void copyArray(int[] arr1, int[] toArr2) {
		for(int i = 0; i<arr1.length; i++) {
			toArr2[i] = arr1[i];
		}
	}
}
