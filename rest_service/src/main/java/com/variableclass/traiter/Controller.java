package com.variableclass.traiter;

import com.google.gson.*;
import com.variableclass.traiter.models.*;
import org.springframework.web.bind.annotation.*;

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
	  
	  try {
		    
	    // Retrieve nature label from JSON request body
	    JsonObject jsonIntent = json.get("intent").getAsJsonObject();
	    JsonObject jsonSlots = jsonIntent.get("slots").getAsJsonObject();
	    JsonObject jsonNature = jsonSlots.get("Nature").getAsJsonObject();
	    String natureLabel = jsonNature.get("value").getAsString();
	    
	    // Retrieve nature of Alexa-provided label
	    return randomiser.getNatureFromLabel(natureLabel);
	    
    } catch (NullPointerException e) {
    	
    }
	  
	  return null;
  }
  
  private Category getCategoryFromJson(JsonObject json, TraitRandomiser randomiser) {
	  
  	try {
      
	    // Retrieve category label from JSON request body
	    JsonObject jsonIntent = json.get("intent").getAsJsonObject();
	    JsonObject jsonSlots = jsonIntent.get("slots").getAsJsonObject();
	    JsonObject jsonCategory = jsonSlots.get("Category").getAsJsonObject();
	    String categoryLabel = jsonCategory.get("value").getAsString();
	    
	    // Retrieve category of Alexa-provided label
	    return randomiser.getCategoryFromLabel(categoryLabel);
	    
    } catch (NullPointerException e) {
    	
    }
  	
  	return null;
  }
}
