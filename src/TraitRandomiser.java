import java.util.*;

public class TraitRandomiser {

	public static void main(String[] args) {
		
		// Create new List to hold Traits
		ArrayList<Trait> traits = new ArrayList<Trait>();
		
		// Create three new traits
		Trait active = new Trait("Active", "Lifestyle", 1);
		Trait cheerful = new Trait("Cheerful", "Lifestyle", 1);
		Trait mean = new Trait("Mean", "Social", -1);
		
		// Add each trait to the traits list
		traits.add(active);
		traits.add(cheerful);
		traits.add(mean);
		
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
