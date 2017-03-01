///////////////////////////////////////////////////////////////////////////////
//                
// Main Class File:  Game.java
// File:             PortalPair.java
// Semester:         Fall 2015
//
// Author:           Jacob Clausen <clausen3@wisc.edu>
//
//
///////////////////////////////////////////////////////////////////////////////

/**
 * The PortalPair class represents a pair of portals.
 * 
 * The Game class instantiates this class once for each pair of portals present
 * when a new level is loaded.
 */
public class PortalPair 
{
	// Private field to hold the GraphicObject associated with the orange portal
	// Private field to hold the GraphicObject associated with the blue portal
	private GraphicObject bluePortals;
	private GraphicObject orangePortals;



	/**
	 * Initializes the GraphicObjects associated with the blue and orange
	 * portals, sets their type, and sets their X and Y coordinates
	 * 
	 * @param name		name displayed on each end of the portal pair
	 * @param blueX		the x position of the blue portal
	 * @param blueY		the y position of the blue portal
	 * @param orangeX	the x position of the orange portal
	 * @param orangeY	the y position of the orange portal
	 */
	public PortalPair(String name, float blueX, float blueY, 
			float orangeX, float orangeY)
	{

		//Orange portals
		orangePortals  = new GraphicObject ("ORANGE_PORTAL_" 
				+ name, orangeX, orangeY); 

		//Blue portals
		bluePortals = new GraphicObject ("BLUE_PORTAL_"+ name, blueX, blueY);
	}


	/**
	 * Checks if either end of this portal pair is colliding with the specified
	 * snake.
	 * 
	 * If either end of this portal pair is colliding with the snake, moves the
	 * snake past the other end of the portal.
	 * 
	 * 
	 * @param snake		the snake to check for collisions with
	 */
	public void teleportSnakeIfColliding(Snake snake)
	{

		if (snake.getGraphicObject().isCollidingWith(orangePortals))
		{
			snake.getGraphicObject().movePast(bluePortals);
		}
		if (snake.getGraphicObject().isCollidingWith(bluePortals))
		{
			snake.getGraphicObject().movePast(orangePortals);
		}

	}
}