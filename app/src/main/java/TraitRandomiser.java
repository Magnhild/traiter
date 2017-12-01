package main.java;
import java.sql.*;
import java.util.*;

public class TraitRandomiser {

	// Attributes
	private String mDatabaseConnection;
	
	// Constructors
	public TraitRandomiser(String databaseConnection) {
		
		mDatabaseConnection = databaseConnection;
	}
	
	// Methods
	public Trait generateTrait(int nature) {

		// Retrieve traits from database
		ArrayList<Trait> traits = TraitRandomiser.getTraitsFromDatabase(mDatabaseConnection);
		
		if (nature != 4) {

			// Determine subset of traits to choose from
			traits =  filterTraitList(nature, traits);
		}

		// If trait list is empty, give error message
		if (traits.isEmpty()) {

			System.out.println("No traits found in this category.");
			return null;
		}

		// Determine index of trait to get
		int index = generateRandomNumber(0, (traits.size() - 1));

		// Retrieve a trait from the list
		Trait randomlySelectedTrait = traits.get(index);

    return randomlySelectedTrait;
	}

	private static ArrayList<Trait> filterTraitList(int nature, ArrayList<Trait> traits) {

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

	private static ArrayList<Trait> getTraitsFromDatabase(String databaseConnection) {

		ArrayList<Trait> traitList = new ArrayList<Trait>();

		// Retrieve data from database
		String query = "SELECT T.id AS id, T.name AS name, C.name AS category, T.nature AS nature FROM TaTraits AS T LEFT JOIN TaCategories AS C ON T.category_id = C.id;";
		ResultSet results = executeQueryOnDatabase(databaseConnection, query);

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
