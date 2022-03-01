import java.util.ArrayList;
import java.util.Random;

public class Assembler {

	public static void main(String[] args) {
		Shelf MS = new Shelf(10,10,.5,.05);
		MS.printShelf();
		printBoxes(getRandomBoxes(10,10,5));
		

	}

	
	
	//TODO: Should have a class for these
	
	public static ArrayList<Box> getRandomBoxes(int maxHeight, int maxWidth, int length){
		ArrayList<Box> ar = new ArrayList<Box>();
		Random rand = new Random();
		for(int i = 0; i<length; i++) {
			ar.add(new Box(rand.nextInt(maxHeight+1),rand.nextInt(maxWidth+1)));
		}
		
		return ar;
	}
	
	public static void printBoxes(ArrayList<Box> boxes) { /* TODO: should be a resolution to print*/
		for(int i = 0; i<boxes.size(); i++) {
			boxes.get(i).printBox();
			System.out.printf("\n");
		}
	}
}
