import java.sql.*;
import java.util.*;

public class TraitRandomiser {

	// Attributes
	private static Scanner inputScanner;
	private static ArrayList<Trait> traits;

	public static void main(String[] args) {

		inputScanner = new Scanner(System.in);

		// Retrieve traits from database
		traits = getTraitsFromDatabase();

    // Perform trait generation
    do {

      int nature = retrieveNatureFromUser();

  		// Perform trait generation
  		generateTrait(nature);

      // Print out the random trait
      System.out.println(randomlySelectedTrait.getName());
      System.out.println();

    } while (confirmGenerateAgain());
	}

	private static Trait generateTrait(int nature) {

		ArrayList<Trait> listToChooseFrom = traits;

		if (nature != 4) {

			// Determine subset of traits to choose from
			listToChooseFrom =  filterTraitList(nature);
		}

		// If trait list is empty, give error message
		if (listToChooseFrom.isEmpty()) {

			System.out.println("No traits found in this category.");
			return;
		}

		// Determine index of trait to get
		int index = generateRandomNumber(0, (listToChooseFrom.size() - 1));

		// Retrieve a trait from the list
		Trait randomlySelectedTrait = listToChooseFrom.get(index);

    return randomlySelectedTrait;
	}

  private static boolean confirmGenerateAgain(){

    boolean generateAgain;

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

    return generateAgain;
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

	private static ArrayList<Trait> getTraitsFromDatabase() {

		ArrayList<Trait> traitList = new ArrayList<Trait>();

		// Retrieve data from database
		ResultSet results = executeQueryOnDatabase("jdbc:sqlserver://localhost;databaseName=TraitsDB;user=traits_app;password=12345", "SELECT T.id AS id, T.name AS name, C.name AS category, T.nature AS nature FROM TaTraits AS T LEFT JOIN TaCategories AS C ON T.category_id = C.id;");

		// Retrieve traits from database result set
		try {

			while (results.next()) {

				int traitId = results.getInt("id");
				String traitName = results.getString("name");
				String traitCategory = results.getString("category");
				int traitNature = results.getInt("nature");

				Trait trait = new Trait(traitId, traitName, traitCategory, traitNature);
				traitList.add(trait);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return traitList;
	}

	private static ResultSet executeQueryOnDatabase(String connectionString, String query) {

		ResultSet resultSet = null;

		try {

			// Load driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		try {

			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return resultSet;
	}
}
