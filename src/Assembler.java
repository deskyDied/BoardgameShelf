import java.util.ArrayList;

//NOTE: My resolution rounds down. 1.67 @ .25 = 1.5

public class Assembler {

	public static void main(String[] args) {
		double res = .25;
		Shelf shelf = new Shelf(24, 36, .5, res);
		//shelf.printShelf();
		
		Boxes boxes = new Boxes(4, 12, true, 40, res);
		//boxes.printBoxesDimensions();
		
		ShelfFiller shelfFiller = new ShelfFiller(0);
		shelfFiller.fillShelfStandard(shelf, boxes, 1);
		//shelf.print();
		shelf.clear();
		System.out.println();
		shelfFiller.fillShelfOptimizeRandom(shelf,  boxes,  1, 100);
		shelf.print();

	}
}
	

