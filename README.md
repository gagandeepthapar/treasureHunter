# **CSC - 203 Final Project**
**Project-Based Object-Oriented Programming and Design:** Development of a game written in Java using the concepts taught in the course.

## **Background**
CSC-203, or Project-Based Object-Oriented Programming and Design, is a course taught at the California Polytechnic State University at San Luis Obispo (Cal Poly SLO) which serves as an...
> *"Introduction to class design, interfaces, inheritance, generics, exceptions, streams, and testing." - Cal Poly Computer Science Catalog*

The final project was a culmination of the material taught in the course. The objective was to create a game (from scratch) for users to play. The game must meet certain criteria (outlined in a later section) and was an individual effort over the course of 1.5 weeks.

I decided to create a simple game where the player would select a model to play as, use the arrow keys (or WASD) to collect coins while avoiding enemies. The player is able to "attack" the enemies, although they will respawn in the center of the screen. The **Win Condition** is to collect all of the coins. The **Lose Condition** is to be attacked by one of the enemies. It should be noted that each of the enemies track down the player using a specified pathing algorithm (either Single Step, A-Star, or a primitive distance closer) as required by the project criteria. 

## **How to Run**
Anyone who wants to play the game for themselves should download the repository. The necessary JAR libaries can be located in the following filepath:
```
/SupportingFiles/JAR_LIBS/
```
The ``TreausureHunter.java`` file contains the main function to run the project. It will call on the ``mainMenu.java`` and ``Game.java`` files as part of its design. Instructions to play can be found both in the game and in the ``gameInfo.pdf`` file.

## **Criteria for Project**
To be considered successful, the project must follow a list of criteria:
* The world must look completely different [than a previous sample project]
* There should be some world changing event and a file to explain the trigger and what should happen
* Users can use arrow keys (or WASD) to control the player
* There should be at least 3 different entities with a unique pathing algorithm
* Use the Factory Design Pattern

These criteria were met and an explanation can be found in the ``gameInfo.pdf`` file.

## **Notable Files**
* ``gameInfo.pdf``: Explanation of the game and how the project criteria was met
* ``worldEventInfo.pdf``: Explanation of the world-event trigger and what the expected output(s) are
* ``TreasureHunter.pdf/.uml``: UML diagram of the project using standard notation