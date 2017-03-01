///////////////////////////////////////////////////////////////////////////////
//
// Main Class File:  Game.java
// File:             Apple.java
// Semester:         Fall 2015
//
// Author:           Jacob Clausen <clausen3@wisc.edu>

//
///////////////////////////////////////////////////////////////////////////////

/**
 * The Apple class represents an apple in the game.
 * 
 * The Game class instantiates this class once for each apple present when a new
 * level is loaded.
 */
public class Apple 
{
	//  holds the GraphicObject associated with this apple
	private GraphicObject apple;  

	/**
	 * Initializes a new Apple object.
	 * 
	 * @param x		the x position of the apple
	 * @param y		the y position of the apple
	 */
	public Apple(float x, float y)
	{

		// Apple's associated GraphicObject with type "APPLE" 
		// at this apple's x and y coordinates.
		this.apple = new GraphicObject ("APPLE",x,y);

	}

	/**
	 * Checks if this apple is colliding with the specified snake.
	 * 
	 * If the GraphicObject associated with this apple is colliding with the
	 * GraphicObject associated with the specified snake's head, grows the snake,
	 * destroys the GraphicObject associated with this apple (causing it to
	 * disappear from the screen), and returns true. Otherwise, returns false.
	 * 
	 * TODO: Implement this.
	 * 
	 * @param snake		snake to check for collisions with
	 * @return true after eating an apple, otherwise false
	 */
	public boolean getEatenBySnakeIfColliding(Snake snake)
	{
		// If GraphicObject of the snake's head is colliding with an apple,
		// The grow() method is called and true is returned.
		if (snake.getGraphicObject().isCollidingWith(apple))
		{
			apple.destroy();
			snake.grow();
			return true;
		}
		// Otherwise, false
		return false;
	}	
}
