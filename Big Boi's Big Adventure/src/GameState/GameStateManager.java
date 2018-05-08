package GameState;

import java.util.ArrayList;


public class GameStateManager
{

    private ArrayList<GameState> gameStates;

    private int currentState;

    public static final int MENUSTATE = 0;

    public static final int LEVEL1STATE = 1;

    // Constructor
    public GameStateManager()
    {

        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        gameStates.add( new MenuState( this ) );
        gameStates.add( new Level1State( this ) );

    }

    // Sets the state of the game
    public void setState( int state )
    {
        currentState = state;
        gameStates.get( currentState ).init();

    }

    // Updates the game state
    public void update()
    {
        gameStates.get( currentState ).update();
    }

    // Drwas the current state of the game
    public void draw( java.awt.Graphics2D g )
    {
        gameStates.get( currentState ).draw( g );
    }

    // Gets if a key is pressed on the current game state
    public void keyPressed( int k )
    {
        gameStates.get( currentState ).keyPressed( k );
    }

    // Gets if a key is released on the current game state
    public void keyReleased( int k )
    {
        gameStates.get( currentState ).keyReleased( k );
    }

}
