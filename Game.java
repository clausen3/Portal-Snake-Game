///////////////////////////////////////////////////////////////////////////////
//                  
// Title:            portalSnake
//
// Files:            Game.java
//                   Apple.java
//					 PortalPair.java
//                   Snake.Java
//                   Rock.java
//
// 
// Author:           Jacob Clausen
// Email:            clausen3@wisc.edu
//
///////////////////////////////////////////////////////////////////////////////
import java.util.*;

/**
 * The Game class represents a running instance of the PortalSnake game. It
 * keeps track of the Snake object, lists of Apple, Rock, and PortalPair
 * objects, the current score, and whether the player has won.
 * 
 * The game engine will create a new instance of
 * this class when the player chooses a level to play. 
 * 
 * At each iteration of the game loop, the game engine calls the update() method
 * in the Game class. The update() method tells each of the objects in the game
 * to update itself based on the rules of the game. It then checks if the game
 * is over or not.
 */
public class Game 
{	
	//Instances of game objects stored in these member variables
	private Snake snake;
	private ArrayList<Apple> appleA;		
	private ArrayList<Rock> rockA;			
	private ArrayList<PortalPair> portalPairs;	

	// Member variables
	private int controlType; // User picks how to control snake
	private int applesEaten = 0; //Initial amount of apples destroyed
	private Random random = new Random(); //For random positioning of objects


	/**
	 * An instance of the Game class. Player chooses a level to play, and the
	 * controlType.
	 * 
	 * @param level - "RANDOM" or descriptions of the object to load
	 * @param controlType - 1: classic, 2: analog, 3: slither
	 */
	public Game(String level, int controlType)
	{
		// Member variables initialized 
		this.controlType = controlType;
		portalPairs = new ArrayList<PortalPair>();
		rockA = new ArrayList<Rock>();
		appleA = new ArrayList<Apple>();


		// load the objects described in the string level
		createRandomLevel();
	}

	/**
	 * Creates a new level with randomly positioned:
	 * Snake(1), Rocks(20), Apples(8), and PortalPairs(3)
	 */
	public void createRandomLevel()
	{
		Rock rock;
		PortalPair portal;
		Apple apple;

		// create a snake 
		snake = new Snake(Engine.getWidth() / 2, Engine.getHeight() / 2);

		// Randomly positioned pair of portals containing a labeled blue and a 
		// corresponding orange
		// First pair
		portal = new PortalPair("A", random.nextFloat() * Engine.getWidth(),
				random.nextFloat() * Engine.getHeight(),
				random.nextFloat() * Engine.getWidth(),
				random.nextFloat() * Engine.getHeight());
		portalPairs.add(portal);

		// Second pair
		portal = new PortalPair("B", random.nextFloat() * Engine.getWidth(),
				random.nextFloat() * Engine.getHeight(),
				random.nextFloat() * Engine.getWidth(),
				random.nextFloat() * Engine.getHeight());
		portalPairs.add(portal);

		// Third pair
		portal = new PortalPair("C", random.nextFloat() * Engine.getWidth(),
				random.nextFloat() * Engine.getHeight(),
				random.nextFloat() * Engine.getWidth(),
				random.nextFloat() * Engine.getHeight());
		portalPairs.add(portal);



		// 8 randomly positioned apples placed
		for (int i = 0; i < 8; i++) 
		{
			apple = new Apple(random.nextFloat() * Engine.getWidth(),
					random.nextFloat() * Engine.getHeight());
			appleA.add(apple);
		}


		// 20 randomly positioned rocks placed
		for (int j = 0; j < 20; j++)
		{
			rock = new Rock(random.nextFloat() * Engine.getWidth(),
					random.nextFloat() * Engine.getHeight());
			rockA.add(rock);
		}

	}

	/**
	 * Loads a level from a String description.
	 * 
	 * Initializes all of the class private fields which hold the Snake object
	 * and the lists of Apple, Rock, and PortalPair objects from the provided
	 * String which contains  
	 * 
	 * TODO: Implement this method
	 * 
	 * @param level - a string containing the names and locations of objects
	 */
	public void loadLevel(String level)
	{
		// Initialize Rock, Apple, and PortalPair ArrayLists
		ArrayList<Rock> rocks = new ArrayList<Rock>();
		ArrayList<Apple> apples = new ArrayList<Apple>();
		ArrayList<PortalPair> portals = new ArrayList<PortalPair>();

		// Create a new scanner to read the level description
		Scanner scan = new Scanner(level);
		// Loop through lines in the level description
		while (scan.hasNextLine()) 
		{

		}
		// Get the next line
		// Split the line into tokens			
		// Determine the type of object to add to the level
		// If it's a snake, create a new snake at the x and y
		// coordinates specified by the second and third tokens

		// If it's an apple, create a new apple at the x and y
		// coordinates specified by the second and third tokens, and add
		// it to the list of apples

		// If it's a rock, create a new rock at the x and y coordinates
		// specified by the second and third tokens and add it to the
		// list of rocks

		// If it's a portal pair, create a new PortalPair with the
		// name equal to the second token, with the first portal at the
		// x and y coordinates specified by the third and fourth
		// tokens, and the second portal at the x and y coordinates
		// specified by the fifth and sixth tokens

		// If it's anything else, ignore it.

		// Close the scanner		
	}

	/**
	 * Updates the game objects.
	 * 
	 * Goes through each of the objects--the snake, rocks, apples, and portals--
	 * and tells them to behave according to the rules of the game. This method
	 * returns true if the game should continue, or false if the game is over.
	 * 
	 * @return - false if the game is over, otherwise true
	 */
	public boolean update()
	{
		// Tell the snake to update itself
		snake.updateMoveDirection(controlType);

		// Tell the snake to die if it's colliding with itself
		snake.dieIfCollidingWithOwnBody();

		// For each rock, tell the rock to kill the snake if the two are
		// colliding
		for (int j = 0; j < rockA.size(); j++)
		{
			rockA.get(j).killSnakeIfColliding(snake);
			if (snake.isDead())
			{
				return false;
			}
		}


		// For each apple, tell the apple to be eaten by the snake if the two
		// are colliding, and if so update the score
		for (int j = 0; j < appleA.size(); j++)
		{
			if (appleA.get(j).getEatenBySnakeIfColliding(snake))
			{
				applesEaten = applesEaten + 1;
				appleA.remove(j);
			}
		}


		// For each portal pair, tell the pair to teleport the snake if the two
		// are colliding
		for (int j = 0; j < portalPairs.size(); j++)
		{
			portalPairs.get(j).teleportSnakeIfColliding(snake);
		}



		// Checks for win/lose by checking playerHasWon() method
		// Game will continue when true is returned.
		// playerHasWon() returns false if the snake has not destroyed 8 apples
		if (playerHasWon() == true)
		{
			return false;  // game ends
		}

		return true; 

	}

	/**
	 *  Returns true if the player has won.
	 * 
	 * TODO: Implement this
	 * 
	 * @return true when the player has won, and false when they have lost or
	 * the game is not over
	 */
	public boolean playerHasWon()
	{
		if (applesEaten == 8) 
		{
			return true; 
		}
		if (snake.isDead()) //Snake will die by colliding with itself or rocks
		{
			return false; 
		}
		else 
		{
			return false;
		}
	}


	/**
	 * Returns the player's score.
	 * 
	 * @return the current score (number of apples eaten)
	 */
	public int getScore()
	{
		return applesEaten; //Player's score is incremented by 1 per apple eaten
	}	


	/**
	 * The main method calls
	 * Engine.startEngineAndGame(), which in turn starts the Engine and creates
	 * an instance of this Game class.  The engine will then repeatedly call
	 * the update() method on this Game until it returns false.
	 *  
	 *  
	 * @param args - command line arguments
	 */
	public static void main(String[] args)
	{
		Application.startEngineAndGame(true);
	}
}
