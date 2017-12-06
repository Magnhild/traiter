package com.variableclass.traiter.models;

public class Category {

  // Attributes
  private int mId;
  private String mLabel;


  // Constructors
  public Category(int id, String label){

    mId = id;
    mLabel = label;
  }


  // Methods
  public int getId(){

    return mId;
  }

  public String getLabel(){

    return mLabel;
  }

  public void setLabel(String label){

    mLabel = label;
  }
}
