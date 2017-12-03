package com.variableclass.traiter;

import main.java.Trait;
import main.java.TraitRandomiser;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

  @RequestMapping(
          value = "/traits/generate",
          method = RequestMethod.GET,
          produces = "application/json"
  )
  public Trait getTrait(@RequestParam(name = "nature", defaultValue = "4") int nature){

    TraitRandomiser randomiser = new TraitRandomiser("");

    Trait trait = randomiser.generateTrait(nature);

    return randomiser.generateTrait(nature);
  }
}
