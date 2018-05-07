package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;


public class MenuState extends GameState
{

    private Background bg;

    private int currentChoice = 0;

    private String[] options = { "Start", "Help", "Quit" };

    private Color titleColor;

    private Font titleFont;

    private Font font;

    // Constructor
    public MenuState( GameStateManager gsm )
    {
        /**
         * TODO change font
         */
        this.gsm = gsm;

        try
        {

            bg = new Background( "/Backgrounds/bgTitlescreen.jpg", 1 );
            bg.setVector( -1, 0 );

            titleColor = new Color( 128, 0, 0 );
            titleFont = new Font( "Century Gothic", Font.PLAIN, 28 );

            font = new Font( "Arial", Font.PLAIN, 12 );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

    }


    public void init()
    {
    }

    // Updates the background
    public void update()
    {
        bg.update();
    }

    // Draws the background as well as the text with font and colors
    public void draw( Graphics2D g )
    {
        /**
         * TODO Change number of times image is printed out
         */
        // draw bg
        bg.draw( g );

        // draw title
        g.setColor( titleColor );
        g.setFont( titleFont );
        g.drawString( "Big Boi's big Adventure", 325, 70 );

        // draw menu options
        g.setFont( font );
        for ( int i = 0; i < options.length; i++ )
        {
            if ( i == currentChoice )
            {
                g.setColor( Color.GREEN );
            }
            else
            {
                g.setColor( Color.RED );
            }
            g.drawString( options[i], 450, 140 + i * 15 );
        }

    }

    // Allows selection of one of three menu options
    private void select()
    {
        if ( currentChoice == 0 )
        {
            gsm.setState( GameStateManager.LEVEL1STATE );
        }
        if ( currentChoice == 1 )
        {
            // help
        }
        if ( currentChoice == 2 )
        {
            System.exit( 0 );
        }

    }

    // Checks for keys being pressed to cycle through menu options
    public void keyPressed( int k )
    {
        if ( k == KeyEvent.VK_ENTER )
        {
            select();
        }
        if ( k == KeyEvent.VK_UP )
        {
            currentChoice--;
            if ( currentChoice == -1 )
            {
                currentChoice = options.length - 1;
            }
        }
        if ( k == KeyEvent.VK_DOWN )
        {
            currentChoice++;
            if ( currentChoice == options.length )
            {
                currentChoice = 0;
            }
        }
    }


    public void keyReleased( int k )
    {
    }

}
