package main.java;

public class Trait {

	// Attributes
	private int mId; 
	private String mName;
	private int mCategory;
	private int mNature;
	
	
	// Constructor
	public Trait(int id, String name, int category, int nature) {
		
		mId = id;
		mName = name;
		mCategory = category;
		
		if ((nature < 2) && (nature > -2)) {
			
			mNature = nature;
			
		} else {
			
			mNature = 0;
		}
	}
	
	
	// Methods
	public int getId() {
		
		return mId;
	}
	
	public String getName() {
		
		return mName;	
	}
	
	public int getCategory() {
		
		return mCategory;
	}
	
	public int getNature() {
		
		return mNature;
	}
	
	public void setName(String name) {
		mName = name;		
	}
	
	public void setCategory(int category) {
		mCategory = category; 
	}

	public void setNature(int nature) {
		if ((nature < 2) && (nature > -2)) {
			mNature = nature; 
		}
	}
}
