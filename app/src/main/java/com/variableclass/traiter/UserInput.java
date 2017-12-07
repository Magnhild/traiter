package com.variableclass.traiter;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.variableclass.traiter.models.*;

public class UserInput {

	// Attributes
	private static Scanner inputScanner;

	// Methods
	public static void main(String[] args) {

		TraitRandomiser randomiser = new TraitRandomiser();

		// Create scanner to read input from user
		inputScanner = new Scanner(System.in);

		Trait trait;

		// Perform trait generation
		do {

			Nature nature = retrieveNatureFromUser(randomiser.getNatures());
			Category category = retrieveCategoryFromUser(randomiser.getCategories());

			// Perform trait generation
			trait = randomiser.generateTrait(nature, category);

			if (trait == null) {

				System.out.println("No traits found in this category.");
				continue;
			}

			// Print out the random trait
			System.out.println(trait.getName());
			System.out.println();

		} while (confirmGenerateAgain());
	}

	private static Nature retrieveNatureFromUser(ArrayList<Nature> natures) {

		Nature returnNature = null;

		// Read user choice of trait nature and validate it is correct
		boolean validInput;
		do {

			// Ask for user input of trait nature
			System.out.println("Which nature would you prefer?");

			// Iterate through all natures and display to user
			int index = 1;
			for (Nature nature : natures) {

				System.out.println(index + ". " + nature.getLabel());
				index++;
			}

			System.out.println(index + ". Random");

			// Read user input and validate it is numerical
			int userInput = 0;

			try {

				userInput = inputScanner.nextInt();
				inputScanner.nextLine();

			} catch (InputMismatchException ex) {

				System.out.println("Input must be numerical!");
				inputScanner.nextLine();
			}

			// Check if user input is a valid selection
			if ((userInput > 0) && (userInput <= natures.size())) {

				returnNature = natures.get(userInput - 1);
				validInput = true;

			} else if ((userInput == (natures.size() + 1))){
				
				returnNature = null;
				validInput = true;
				
			} else {

				System.out.println("Input should be one of the displayed numbers!");
				validInput = false;
			}

		} while (!validInput);

		return returnNature;
	}

	private static Category retrieveCategoryFromUser(ArrayList<Category> categories) {

		Category returnCategory = null;

		// Read user choice of trait category and validate it is correct
		boolean validInput;
		do {

			// Ask for user input of trait nature
			System.out.println("Which category would you prefer?");

			// Iterate through all categories and display to user
			int index = 1;
			for (Category category : categories) {

				System.out.println(index + ". " + category.getLabel());
				index++;
			}

			System.out.println(index + ". Random");

			// Read user input and validate it is numerical
			int userInput = 0;

			try {

				userInput = inputScanner.nextInt();
				inputScanner.nextLine();

			} catch (InputMismatchException ex) {

				System.out.println("Input must be numerical!");
				inputScanner.nextLine();
			}

			// Check if user input is a valid selection
			if ((userInput > 0) && (userInput <= categories.size())) {

				returnCategory = categories.get(userInput - 1);
				validInput = true;
								
			} else if ((userInput == (categories.size() + 1))){
				
				returnCategory = null;
				validInput = true;

			} else {

				System.out.println("Input should be one of the displayed numbers!");
				validInput = false;
			}

		} while (!validInput);

		return returnCategory;
	}

	private static boolean confirmGenerateAgain() {

		boolean generateAgain = false;

		// Ask user if they wish to generate another trait
		boolean inputIsYesOrNo;
		do {

			// Check if user would like another trait
			System.out.println("Would you like another? (Y/n)");

			// Read their response
			String userResponse = inputScanner.nextLine();

			// Check if input is empty
			switch ((userResponse.trim())) {
			case "n":
				generateAgain = false;
				inputIsYesOrNo = true;
				break;

			case "y":
				generateAgain = true;
				inputIsYesOrNo = true;
				break;

			default:
				System.out.println("Input should be 'y' or 'n'");
				inputIsYesOrNo = false;
				break;
			}

		} while (!inputIsYesOrNo);

		return generateAgain;
	}

}
