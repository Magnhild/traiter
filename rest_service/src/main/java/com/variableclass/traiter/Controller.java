package com.variableclass.traiter;

import com.google.gson.*;
import com.variableclass.traiter.models.*;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
public class Controller {

  @RequestMapping(
          value = "/alexa",
          method = RequestMethod.POST,
          produces = "application/json"
  )
  public String getTrait(@RequestBody String requestBody){
	
	// Parse request body string to JSON
    JsonParser parser = new JsonParser();
    JsonObject jsonRequestBody = parser.parse(requestBody).getAsJsonObject();
    
    // Initalise new trait randomiser
    TraitRandomiser randomiser = new TraitRandomiser();
    
    // Attempt to retrieve any filters from the JSON
    Nature nature = getNatureFromJson(jsonRequestBody, randomiser);
    Category category = getCategoryFromJson(jsonRequestBody, randomiser);
    
    // Generate a trait
    Trait trait = randomiser.generateTrait(nature, category);

    String traitSentence = "Your trait is " + trait.getName();

    JsonObject outputSpeech = new JsonObject();
    outputSpeech.addProperty("type", "PlainText");
    outputSpeech.addProperty("text", traitSentence);

    JsonObject card = new JsonObject();
    card.addProperty("type", "Simple");
    card.addProperty("title", "Trait: " + trait.getName());
    card.addProperty("content", traitSentence);

    String version = jsonRequestBody.get("version").getAsString();

    JsonObject sessionAttributes = new JsonObject();

    JsonObject response = new JsonObject();
    response.add("outputSpeech", outputSpeech);
    response.add("card", card);
    response.addProperty("shouldEndSession", true);
    
    JsonObject jsonResponse = new JsonObject();
    jsonResponse.addProperty("version", version);
    jsonResponse.add("sessionAttributes", sessionAttributes);
    jsonResponse.add("response", response);

    return jsonResponse.toString();
  }
  
  private Nature getNatureFromJson(JsonObject json, TraitRandomiser randomiser) {

    String natureLabel = null;

	  try {
		    
	    // Retrieve nature label from JSON request body
      JsonObject jsonRequest = json.get("request").getAsJsonObject();
      JsonObject jsonIntent = jsonRequest.get("intent").getAsJsonObject();
	    JsonObject jsonSlots = jsonIntent.get("slots").getAsJsonObject();
	    JsonObject jsonNature = jsonSlots.get("Nature").getAsJsonObject();
	    natureLabel = jsonNature.get("value").getAsString().toUpperCase();
	    
    } catch (NullPointerException e) {
    	
    }

    ArrayList<String> positiveSynonyms = new ArrayList<>();
    positiveSynonyms.add("GOOD");
    positiveSynonyms.add("HAPPY");
    positiveSynonyms.add("FAVOURABLE");
    positiveSynonyms.add("NICE");
    positiveSynonyms.add("A+");
    positiveSynonyms.add("ACE");
    positiveSynonyms.add("BRILLIANT");
    positiveSynonyms.add("WONDERFUL");
    positiveSynonyms.add("GREAT");
    positiveSynonyms.add("MARVELOUS");
    positiveSynonyms.add("LOVELY");
    positiveSynonyms.add("AMAZING");

    ArrayList<String> neutralSynonyms = new ArrayList<>();
    neutralSynonyms.add("NORMAL");
    neutralSynonyms.add("STANDARD");
    neutralSynonyms.add("BASIC");
    neutralSynonyms.add("VANILLA");

    ArrayList<String> negativeSynonyms = new ArrayList<>();
    negativeSynonyms.add("BAD");
    negativeSynonyms.add("SHIT");
    negativeSynonyms.add("RUBBISH");
    negativeSynonyms.add("CRAP");
    negativeSynonyms.add("NASTY");
    negativeSynonyms.add("AWFUL");
    negativeSynonyms.add("LAME");
    negativeSynonyms.add("LOUSY");
    negativeSynonyms.add("NAFF");

    if (positiveSynonyms.contains(natureLabel)){

      natureLabel = "POSITIVE";

    } else if (neutralSynonyms.contains(natureLabel)){

      natureLabel = "NEUTRAL";

    } else if (negativeSynonyms.contains(natureLabel)){

      natureLabel = "NEGATIVE";
    }

    // Retrieve nature of Alexa-provided label
    return randomiser.getNatureFromLabel(natureLabel);
  }
  
  private Category getCategoryFromJson(JsonObject json, TraitRandomiser randomiser) {
	  
  	try {
      
	    // Retrieve category label from JSON request body
      JsonObject jsonRequest = json.get("request").getAsJsonObject();
	    JsonObject jsonIntent = jsonRequest.get("intent").getAsJsonObject();
	    JsonObject jsonSlots = jsonIntent.get("slots").getAsJsonObject();
	    JsonObject jsonCategory = jsonSlots.get("Category").getAsJsonObject();
	    String categoryLabel = jsonCategory.get("value").getAsString().toUpperCase();
	    
	    // Retrieve category of Alexa-provided label
	    return randomiser.getCategoryFromLabel(categoryLabel);
	    
    } catch (NullPointerException e) {
    	
    }
  	
  	return null;
  }
}
