///////////////////////////////////////////////////////////////////////////////
//
// Main Class File:  Game.java
// File:             Snake.java
// Semester:         Fall 2015
//
// Author:           Jacob Clausen <clausen3@wisc.edu>
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;

/**
 * The Snake class represents the player-controlled snake. 
 *
 * The Game class instantiates this class exactly once, when a new level is
 * loaded.
 */
public class Snake 
{
	// Private variables to hold the GraphicObject 
	// associated with the snake's head,
	// and an ArrayList of GraphicObject associated with the snake's body
	private boolean dead;
	private GraphicObject head;
	private ArrayList<GraphicObject> body;
	private GraphicObject growSnake;


	/**
	 * Initializes a new Snake object at the specified (x,y) position.
	 * 
	 * 
	 * @param x		the initial x position of the snake
	 * @param y		the initial y position of the snake
	 */
	public Snake(float x, float y)
	{
		// Initialize new ArrayList to hold body segments
		body = new ArrayList<>();

		// Initialize the head
		head = new GraphicObject ( "HEAD", x, y );

		// Set the speed of the head
		head.setSpeed(2);

		// Set the direction of the head
		head.setDirection(90);

		// Add the head to the list of body segments
		body.add(head);

		// Add four body segments (grow the snake four times)
		for (int i = 0; i < 4; i++){
			grow();
		}
	}


	/**
	 * Returns the GraphicObject associated with the head of this snake.
	 *   
	 * @return the GraphicObject associated with the head of this snake
	 */
	public GraphicObject getGraphicObject()
	{
		return head;
	}

	/**
	 * Grows the snake by one body segment.
	 * 
	 */
	public void grow()
	{

		// New GraphicObject with type "BODY"
		growSnake = new GraphicObject( "BODY", 0 , 0 );

		// Finds the last body segment in the list of body segments and
		// Sets the leader of the new body segment to be the last body segment
		// Adds the new body segment to the end of the list of body segments
		growSnake.setLeader( body.get( body.size() - 1) );
		body.add(growSnake);

	}

	/**
	 * Reads keyboard input and changes the snake's direction as necessary.
	 *
	 * 
	 * @param controlType - 1: classic, 2: analog, 3: slither
	 */
	public void updateMoveDirection(int controlType)
	{
		// Graphic Object associated with snake's head changes direction based
		// on user input. controlType is picked by user after running program
		// and before picking a level.
		if (controlType == 1) 
		{
			if (Engine.isKeyPressed("RIGHT"))
			{
				this.head.setDirection(head.getDirection() - 90);
			}
			if (Engine.isKeyPressed("LEFT"))
			{
				this.head.setDirection(head.getDirection() + 90);
			}
		}
		if (controlType == 2)
		{
			if (Engine.isKeyHeld("RIGHT"))
			{
				this.head.setDirection(head.getDirection() - 6);
			}

			if (Engine.isKeyHeld("LEFT")) 
			{
				this.head.setDirection(head.getDirection() + 6);
			}
		}
		if(controlType == 3)
		{
			if (Engine.isKeyHeld("SPACE"))
			{
				this.head.setDirection(head.getDirection() + 6);
			}
			else
			{
				this.head.setDirection(head.getDirection() - 6);
			}
		}

	}

	/**
	 * Kills the snake if the head is colliding with any of the body segments.
	 * 
	 */
	public void dieIfCollidingWithOwnBody()
	{
		// For each object in the body
		for ( int i = 0; i < body.size(); i++ )
		{
			// If head is colliding with this segment
			if (head.isCollidingWith(this.body.get(i)))
			{
				die(); // Snake is killed
			}
		}
	}

	/**
	 * Kills the snake.
	 * 
	 */
	public void die()
	{

		// Snakes head initially dies
		head.setType("DEAD");

		// Each GraphicObject in the snake's body also die
		for ( int i = 0; i < body.size(); i++)
		{
			body.get(i).setType("DEAD");
		}

		dead = true;
	}


	/**
	 * Returns true if the snake is dead.
	 * 
	 * @return true if the snake is dead, false otherwise
	 */
	public boolean isDead()
	{
		return dead;
	}
}

