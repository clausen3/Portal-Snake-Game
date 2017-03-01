///////////////////////////////////////////////////////////////////////////////
//
// Main Class File:  Game.java
// File:             Rock.java
// Semester:         Fall 2015
//
// Author:           Jacob Clausen <clausen3@wisc.edu>

//
///////////////////////////////////////////////////////////////////////////////
/**
 * The Rock class represents a rock in the game.
 * 
 * The Game class instantiates this class once for each rock present when a new
 * level is loaded.
 */
public class Rock 
{
	// Holds the GraphicObject associated with rocks
	private GraphicObject randomRock;

	/**
	 *Initializes the rock's GraphicObject at the rock's respective X and Y 
	 *coordinates.
	 *
	 * @param x		the x position of the rock
	 * @param y		the y position of the rock
	 */
	public Rock(float x, float y)
	{
		randomRock = new GraphicObject ( "ROCK", x, y ); // Rock is placed
	}

	/**
	 * Checks if this rock is colliding with the specified snake.
	 * 
	 * If the GraphicObject associated with this rock is colliding with the head 
	 * of the GraphicObject associated with the head of the snake, kills the 
	 * snake.
	 * 
	 * 
	 * @param snake		snake to check for collisions with
	 */
	public void killSnakeIfColliding(Snake snake)
	{
		// If this rock is colliding with the snake's head's GraphicObject, kill
		// the snake
		if (snake.getGraphicObject().isCollidingWith( randomRock ))
		{
			snake.die();
		}
	}
}