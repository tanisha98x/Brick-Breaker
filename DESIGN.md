**Provide the high-level design goals of your project**
1. *Classes:*
* Dividing up the code into separate classes that each hold a separate responsibility
* Having classes for individual game objects (bricks, bouncers and paddle) that hold the individual object attributes as private instance variables that can be accessed using getter and setter functions, and methods pertaining to the individual objects.
* Having a brick manager class that holds the brick objects in a 2D array
* Having a rules class that holds and updates the score, lives, and level and updates it as the game is being played
* Having a high score class that holds the scores in a file and adds the score after every game, parses over the file and returns the highest score
* Having a splash screen and status display class that each create the objects and hold and update their values

2. *Methods/ Variables:*
* Ensuring that methods not called outside a given class are private and only methods accessed outside a class are public
* Having only static final variables be public and all other instance variables be private that can be accessed by getter and setter functions
* Avoiding duplicated code
* Making use of private helper methods

**Explains, in detail, how to add new features to your project**

1. *Adding a new brick type:*
* Different brick types are identified my unique number id
* The number can be added to the level configuration file, in places where the brick needs to be
* An if statement can be added to recognise the brick type and subsequent methods can then be implemented for the specific features

2. *Adding a level:*
* A level configuration file can be added
* The rules class methods needs to be altered to add level

3. *Adding features to an existing level:*
* Methods for these features can be called every time the level changes

**Justifies major design choices, including trade-offs (i.e., pros and cons), made in your project**


**States any assumptions or decisions made to simplify or resolve ambiguities in your the project's functionality**
