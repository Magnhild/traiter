package main.java;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TraitFileReader {
	
	//Attributes
	private String mFilepath;
	
	
	//constructor
	
	public TraitFileReader(String filepath) {
		
		mFilepath = filepath; 
	}
	
	//Method
	
	public ArrayList<Trait> readFile() {
		
		// Create new trait list
		ArrayList<Trait> traitList = new ArrayList<Trait>();
		
		// Attempt to read file
		try {
			
			// Create new file reader object to read the file it has been passed
			FileReader fileReader = new FileReader(mFilepath);
			BufferedReader bufferedReader= new BufferedReader(fileReader);
			
			String line = null;
			
			// While there is still a line to be read
			while ((line = bufferedReader.readLine()) != null) {
				
				// Split the line based on the commas
				String[] traitArray = line.split(";");
				
				// Store the 3 parts of the line as new variables
				String name = traitArray[0];
				String category = traitArray[1];
				int nature = Integer.parseInt(traitArray[2]);
				
				// Create new trait from these variables
				Trait trait = new Trait(1, name, category, nature);
				
				// Add this new trait to the list that will be returned
				traitList.add(trait);
			}
			
			bufferedReader.close();
			
		} catch(FileNotFoundException ex) {
			
			// Print error to user if the file was not found
			System.out.println("File not found");
		
		} catch(IOException ex) {
			
			// Print error to user if something went wrong whilst reading the file
			System.out.println("Error reading file");
		}
		
		
		return traitList;
		
	}

}
