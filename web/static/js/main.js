var traitRandomiserWebApp = {};
traitRandomiserWebApp.navigation = {};

// Define a constant for the active navigation element class name
const ACTIVE = "active";

// Define and initialise the navigation elements
var navHome = document.getElementById("navbar_home");
var navGenerate = document.getElementById("navbar_generate");
var navAbout = document.getElementById("navbar_about");

// Pass a new function to the onclick of each navigation element
navHome.onclick = function(){

  traitRandomiserWebApp.navigation.selectNavbarItem(navHome);
}
navGenerate.onclick = function(){

  traitRandomiserWebApp.navigation.selectNavbarItem(navGenerate);
}
navAbout.onclick = function(){

  traitRandomiserWebApp.navigation.selectNavbarItem(navAbout);
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
