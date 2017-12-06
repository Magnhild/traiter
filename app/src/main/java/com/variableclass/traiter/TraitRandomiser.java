package com.variableclass.traiter;

import com.variableclass.traiter.models.*;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.*;

public class TraitRandomiser {

  // Attributes
  private ArrayList<Nature> mNatures;
  private ArrayList<Category> mCategories;
  private ArrayList<Trait> mTraits;


	// Constructors
	public TraitRandomiser() {

	  // Initialise attributes with values
	  mNatures = new ArrayList<>();
	  mCategories = new ArrayList<>();
	  mTraits = new ArrayList<>();

	  mTraits = getTraitsFromDatabase();
  }


	// Methods
	public Trait generateTrait(Nature nature, Category category) {

		// Determine subset of traits to choose from
		mTraits = filterTraits(nature, category);

		// If trait list is empty, give error message
		if (mTraits.isEmpty()) {

			return null;
		}

		// Determine index of trait to get
		int index = generateRandomNumber((mTraits.size() - 1));

		// Retrieve a trait from the list
    return mTraits.get(index);
	}

	public Nature getNatureFromId(int id){

	  // Iterate through natures
	  for (Nature nature : mNatures){

	    // If ID matches ID of nature
	    if (nature.getId() == id){

	      return nature;
      }
    }

    return null;
  }

  public Nature getNatureFromLabel(String label){

    // Iterate through natures
    for (Nature nature : mNatures){

      // If label matches label of nature
      if (nature.getLabel().toUpperCase() == label.toUpperCase()){

        return nature;
      }
    }

    return null;
  }

  public Category getCategoryFromId(int id){

    // Iterate through categories
    for (Category category : mCategories){

      // If ID matches ID of category
      if (category.getId() == id){

        return category;
      }
    }

    return null;
  }

  public Category getCategoryFromLabel(String label){

    // Iterate through categories
    for (Category category : mCategories){

      // If ID matches ID of category
      if (category.getLabel().toUpperCase() == label.toUpperCase()){

        return category;
      }
    }

    return null;
  }

  public ArrayList<Nature> getNatures(){

    return mNatures;
  }

  public ArrayList<Category> getCategories(){

    return mCategories;
  }

	private ArrayList<Trait> filterTraits(Nature nature, Category category){

    // Create new list
    ArrayList<Trait> subTraitList = new ArrayList<>();

    // Look through main list
    for (Trait trait : mTraits) {

      // If a nature/category filter exists, but the trait nature/category does not match
      if (((nature != null) && (nature.getId() != trait.getNature().getId())) ||
              ((category != null) && (category.getId() != trait.getCategory().getId()))) {

        // Process the next trait
        continue;
      }

      // Otherwise, add trait to return list
      subTraitList.add(trait);
    }

    return subTraitList;
  }

  private ArrayList<Trait> getTraitsFromDatabase() {

    ArrayList<Trait> traits = new ArrayList<>();

    // Build up database query
    String query = "SELECT T.id AS trait_id, T.name AS trait_name, C.id AS category_id, C.label AS category_label, N.id AS nature_id, N.label AS nature_label " +
            "FROM TaTrait AS T " +
            "LEFT JOIN TaCategory AS C " +
            "ON T.category_id = C.id " +
            "LEFT JOIN TaNature AS N " +
            "ON T.nature_id = N.id;";

    // Retrieve dataset from database
    ResultSet results = executeQueryOnDatabase(query);

    // Create natures, categories and traits from dataset
    try {

      while (results.next()) {

        // Retrieve nature from row
        int natureId = results.getInt("nature_id");
        String natureLabel = results.getString("nature_label");
        Nature traitNature = new Nature(natureId, natureLabel);

        boolean natureExists = false;

        // Check nature does not already exist
        for (Nature nature : mNatures){

          if (nature.getId() == traitNature.getId()){

            natureExists = true;
            break;
          }
        }

        // If nature does not exist
        if (!natureExists){

          // Add it to the list of natures
          mNatures.add(traitNature);
        }

        // Retrieve category from row
        int categoryId = results.getInt("category_id");
        String categoryLabel = results.getString("category_label");
        Category traitCategory = new Category(categoryId, categoryLabel);

        boolean categoryExists = false;

        // Check category does not already exist
        for (Category category : mCategories){

          if (category.getId() == traitCategory.getId()){

            categoryExists = true;
            break;
          }
        }

        // If category does not exist
        if (!categoryExists){

          // Add it to the list of categories
          mCategories.add(traitCategory);
        }

        int traitId = results.getInt("trait_id");
        String traitName = results.getString("trait_name");

        // Create a new trait and add it to the list of traits
        Trait trait = new Trait(traitId, traitName, traitCategory, traitNature);
        traits.add(trait);
      }

    } catch (SQLException e) {

      e.printStackTrace();
    }

    return traits;
  }

	private static int generateRandomNumber(int upperBound) {

		// Create new Random object, from which to draw a new random number
		Random rand = new Random();

		// Using the provided boundaries, generate a new random number
    return rand.nextInt(upperBound + 1);
	}

	private static ResultSet executeQueryOnDatabase(String query) {

		ResultSet resultSet = null;

		try {

			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

		} catch (URISyntaxException | SQLException e) {

			e.printStackTrace();
		}

		return resultSet;
	}

	private static Connection getConnection() throws URISyntaxException, SQLException {

		try {

			// Load driver
			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		String dbUrl = System.getenv("JDBC_DATABASE_URL");

		return DriverManager.getConnection(dbUrl);
	}
}
