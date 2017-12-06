package com.variableclass.traiter.models;

public class Trait {

	// Attributes
	private int mId;
	private String mName;
	private Category mCategory;
	private Nature mNature;


	// Constructors
	public Trait(int id, String name, Category category, Nature nature) {

		mId = id;
		mName = name;
		mCategory = category;
		mNature = nature;
	}


	// Methods
	public int getId() {

		return mId;
	}

	public String getName() {

		return mName;
	}

	public Category getCategory() {

		return mCategory;
	}

	public Nature getNature() {

		return mNature;
	}

	public void setName(String name) {

    mName = name;
	}

	public void setCategory(Category category) {

    mCategory = category;
	}

	public void setNature(Nature nature) {

    mNature = nature;
	}
}
