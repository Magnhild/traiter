var traitRandomiserWebApp = {};
traitRandomiserWebApp.navigation = {};

// Define a constant for the active navigation element class name
const ACTIVE = "active";
const HIDDEN = "hidden";

// Define and initialise the navigation elements
var navHome = document.getElementById("navbar_home");
var navGenerate = document.getElementById("navbar_generate");
var navAbout = document.getElementById("navbar_about");

// Define and initialise the content divs
var divHome = document.getElementById("home");
var divGenerate = document.getElementById("generate");
var divAbout = document.getElementById("about");

// Pass a new function to the onclick of each navigation element
navHome.onclick = function(){

  traitRandomiserWebApp.navigation.selectNavbarItem(navHome);

  traitRandomiserWebApp.navigation.selectDiv(divHome);
}
navGenerate.onclick = function(){

  traitRandomiserWebApp.navigation.selectNavbarItem(navGenerate);

  traitRandomiserWebApp.navigation.selectDiv(divGenerate);
}
navAbout.onclick = function(){

  traitRandomiserWebApp.navigation.selectNavbarItem(navAbout);

  traitRandomiserWebApp.navigation.selectDiv(divAbout);
}

// Helper functions
traitRandomiserWebApp.navigation.deselectAllNavItems = function(){

  // Retrieve all currently selected items and deselect them
  var activeNavItems = document.getElementsByClassName(ACTIVE);

  for (var i = 0; i < activeNavItems.length; i++) {

     activeNavItems[i].classList.remove(ACTIVE);
  };
};
traitRandomiserWebApp.navigation.selectNavbarItem = function(navbarItem){

  traitRandomiserWebApp.navigation.deselectAllNavItems();

  // Select the passed item
  navbarItem.classList.add(ACTIVE);
};

traitRandomiserWebApp.navigation.hideAllDivs = function(){

  // Retrieve all divs and hide them
  var divs = document.getElementsByTagName("div");

  for (var i = 0; i < divs.length; i++){

    divs[i].classList.add(HIDDEN);
  };
};
traitRandomiserWebApp.navigation.selectDiv = function(div){

  traitRandomiserWebApp.navigation.hideAllDivs();

  // Show the passed div
  div.classList.remove(HIDDEN);
};
