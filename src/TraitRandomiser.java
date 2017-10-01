import java.util.*;

public class TraitRandomiser {

	// Attributes
	private static Scanner inputScanner;
	private static ArrayList<Trait> traits;
	
	public static void main(String[] args) {
		
		String filePath = "C:\\Users\\magnh\\Desktop\\traits.csv";
		
		// Prompt user for input
		System.out.println("Please enter the filepath or leave blank to use default: ");
		
		// Read filepath from user
		inputScanner = new Scanner(System.in);
		
		String userFilePath = inputScanner.nextLine();
		
		// Check if input is empty
		if (!(userFilePath.trim()).equals("")) {
			
			filePath = userFilePath;
		}
		
		// Create new file reader to import traits
		TraitFileReader fileReader = new TraitFileReader(filePath);
		
		// Create new List to hold Traits and populate it
		traits = fileReader.readFile();
		
		// Perform trait generation
		generateTrait();
		
		// Close input scanner
		inputScanner.close();
	}

	private static void generateTrait() {
		
		// Perform trait generation
		boolean generateAgain = false;
		do {
		
			int nature = retrieveNatureFromUser();
			
			ArrayList<Trait> listToChooseFrom = traits;
			
			if (nature != 4) {
			
				// Determine subset of traits to choose from
				listToChooseFrom =  filterTraitList(nature);
			} 
			
			// Determine index of trait to get
			int index = generateRandomNumber(0, (listToChooseFrom.size() - 1));
			
			// Retrieve a trait from the list
			Trait randomlySelectedTrait = listToChooseFrom.get(index);
			
			// Print out the random trait
			System.out.println(randomlySelectedTrait.getName());
			System.out.println();
			
			
			// Ask user if they wish to generate another trait
			boolean inputIsYesOrNo;
			do {
			
				// Check if user would like another trait
				System.out.println("Would you like another? (Y/n)");
				
				// Read their response
				String userResponse = inputScanner.nextLine();
				
				// Check if input is empty
				if ((userResponse.trim()).equals("n")) {
					
					generateAgain = false;
					inputIsYesOrNo = true;
					
				} else if ((userResponse.trim()).equals("y")) {
					
					generateAgain = true;
					inputIsYesOrNo = true;
				
				} else {
					
					System.out.println("Input should be 'y' or 'n'");
					inputIsYesOrNo = false;
				}
				
			} while (!inputIsYesOrNo);
			
		} while (generateAgain);
		
	}
	
	private static int retrieveNatureFromUser() {
		
		int userNature = 4;
		
		// Read user choice of trait nature and validate it is correct
		boolean validInput = false;
		do {

			// Ask for user input of trait nature 
			System.out.println("Which nature would you prefer?");
			System.out.println("1. Good");
			System.out.println("2. Neutral");
			System.out.println("3. Bad");
			System.out.println("4. Random");
			
			// Read user input and validate it is numerical
			try {
					
				userNature = inputScanner.nextInt();
				inputScanner.nextLine();
				validInput = true;
			
			} catch (InputMismatchException ex) {
				
				System.out.println("Input must be numerical!");
				inputScanner.nextLine();
				validInput = false;
			}
			
			if (!validInput) {
				
				break;
			}
			
			// Check if user input is a valid selection
			switch (userNature) {
			case 1:
				validInput = true;
				break;
			case 2: 
				userNature = 0;
				validInput = true;
				break;
			case 3: 
				userNature = -1;
				validInput = true;
				break;
			case 4: 
				validInput = true;
				break;
				
			default: 
				System.out.println("Input should be '1', '2', '3' or '4'");
				validInput = false;
				break;
			}
			
		} while (!validInput);
		
		
		
		return userNature;
	}

	private static ArrayList<Trait> filterTraitList(int nature) {
		
		// Create new list
		ArrayList<Trait> subTraitList = new ArrayList<Trait>();
				
		// Look through main list
		for (Trait trait : traits) {
								
			if (nature == trait.getNature()) {
				
				subTraitList.add(trait);
			}
		}
		
		return subTraitList;  
	}
	
	private static int generateRandomNumber(int lowerBound, int upperBound) {
		
		// Create new Random object, from which to draw a new random number
		Random rand = new Random();
		
		// Using the provided boundaries, generate a new random number
		int randomNumber = rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
		
		return randomNumber;
	}
}
