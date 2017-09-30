import java.util.*;

public class TraitRandomiser {

	public static void main(String[] args) {
		
		String filePath = "C:\\Users\\magnh\\Desktop\\traits.csv";
		
		// Prompt user for input
		System.out.println("Please enter the filepath or leave blank to use default: ");
		
		// Read filepath from user
		Scanner inputScanner = new Scanner(System.in);
		
		String userFilePath = inputScanner.nextLine();
		
		// Check if input is empty
		if (!(userFilePath.trim()).equals("")) {
			
			filePath = userFilePath;
		}
		
		inputScanner.close();
		
		// Create new file reader to import traits
		TraitFileReader fileReader = new TraitFileReader(filePath);
		
		// Create new List to hold Traits and populate it
		ArrayList<Trait> traits = fileReader.readFile();
		
		// Determine index of trait to get
		int index = generateRandomNumber(0, (traits.size() - 1));
		
		// Retrieve a trait from the list
		Trait randomlySelectedTrait = traits.get(index);
		
		// Print out the random trait
		System.out.println(randomlySelectedTrait.getName());
	}
	
	private static int generateRandomNumber(int lowerBound, int upperBound) {
		
		// Create new Random object, from which to draw a new random number
		Random rand = new Random();
		
		// Using the provided boundaries, generate a new random number
		int randomNumber = rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
		
		return randomNumber;
	}
}
