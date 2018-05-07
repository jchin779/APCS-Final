package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JPanel;

import GameState.GameStateManager;


public class GamePanel extends JPanel implements Runnable, KeyListener
{

    // dimensions
    public static final int WIDTH = 900;

    public static final int HEIGHT = 550;

    public static final int SCALE = 2;

    // game thread

    private Thread thread;

    private boolean running;

    private int FPS = 60;

    private long targetTime = 1000 / FPS;

    // Image
    private BufferedImage image;

    private Graphics2D g;

    // game state manager
    private GameStateManager gsm;

    // Constructor
    public GamePanel()
    {
        super();
        setPreferredSize( new Dimension( WIDTH * SCALE, HEIGHT * SCALE ) );
        setFocusable( true );
        requestFocus();

    }

    // 
    public void addNotify()
    {
        super.addNotify();
        if ( thread == null )

        {
            thread = new Thread( this );
            addKeyListener( this );
            thread.start();
        }
    }

    // Initializes the game state manager and image
    private void init()
    {
        image = new BufferedImage( WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB );

        g = (Graphics2D)image.getGraphics();

        running = true;

        gsm = new GameStateManager();
    }

    // Runs the game and keeps track of run time
    public void run()
    {

        init();

        long start;
        long elapsed;
        long wait;

        // game loop
        while ( running )
        {
            start = System.nanoTime();
            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;
            if ( wait < 0 )
            {
                wait = 5;
            }
                

            try
            {
                Thread.sleep( wait );

            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }

        }

    }

    // Updates the game state manager
    private void update()
    {
        gsm.update();
    }

    // Draws the game state manager
    private void draw()
    {
        gsm.draw( g );
    }

    // Draws the image to the screen
    private void drawToScreen()
    {
        Graphics g2 = getGraphics();
        g2.drawImage( image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null );
        g2.dispose();
    }

    // 
    public void keyTyped( KeyEvent key )
    {

    }

    // Checks if a key is pressed
    public void keyPressed( KeyEvent key )
    {
        gsm.keyPressed( key.getKeyCode() );
    }

    // Checks if a key is released
    public void keyReleased( KeyEvent key )
    {
        gsm.keyReleased( key.getKeyCode() );

    }

}
