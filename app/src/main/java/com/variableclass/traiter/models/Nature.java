package com.variableclass.traiter.models;

public class Nature {

  // Attributes
  private int mId;
  private String mLabel;


  // Constructors
  public Nature(int id, String label){

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
