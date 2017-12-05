package com.variableclass.traiter;

import com.google.gson.*;
import main.java.Trait;
import main.java.TraitRandomiser;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

  @RequestMapping(
          value = "/traits/generate",
          method = RequestMethod.POST,
          produces = "application/json"
  )
  public String getTrait(@RequestBody String requestBody){

    JsonParser parser = new JsonParser();
    JsonObject jsonRequestBody = parser.parse(requestBody).getAsJsonObject();

    // Generate trait
    TraitRandomiser randomiser = new TraitRandomiser();
    Trait trait = randomiser.generateTrait(4);

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
}
