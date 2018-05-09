package TileMap;

import Main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import Main.GamePanel;


public class Background
{
    private BufferedImage image;

    private double x;

    private double y;

    private double dx;

    private double dy;

    private double moveScale;

    // Constructor
    // @param s String name
    // @param ms Double move scale
    public Background( String s, double ms )
    {
        try
        {
            image = ImageIO.read( getClass().getResourceAsStream( s ) );
            moveScale = ms;
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    // Sets the position of the game panel
    // @param x value
    // @param y value
    public void setPosition( double x, double y )
    {
        this.x = ( x * moveScale ) % GamePanel.WIDTH;
        this.y = ( y * moveScale ) % GamePanel.HEIGHT;
    }

    // Sets the two vectors dx and dy
    // @param dx Double x vector value
    // @param dy Double y vector value
    public void setVector( double dx, double dy )
    {
        this.dx = dx;
        this.dy = dy;
    }

    // Updates x and y with dx and dy vectors
    public void update()
    {
        x += dx;
        y += dy;
    }

    // Draws the background image
    // @param g Graphics component
    public void draw( Graphics2D g )
    {
        /**
         * TODO increase number of pics made
         */
        if ( image == null )
        {
            System.out.println( "IMAGE NULL" );
            System.exit( 0 );
        }
        g.drawImage( image, (int)x, (int)y, null );
        if ( x < 0 )
        {
            
            g.drawImage( image, (int)x + GamePanel.WIDTH, (int)y, null );
            g.drawImage( image, (int)x + GamePanel.WIDTH*2, (int)y, null );
            g.drawImage( image, (int)x + GamePanel.WIDTH*3, (int)y, null );
            g.drawImage( image, (int)x + GamePanel.WIDTH*4, (int)y, null );
        }
        if ( x > 0 )
        {
            g.drawImage( image, (int)x - GamePanel.WIDTH, (int)y, null );
            g.drawImage( image, (int)x - GamePanel.WIDTH*2, (int)y, null );
            g.drawImage( image, (int)x - GamePanel.WIDTH*3, (int)y, null );
            g.drawImage( image, (int)x - GamePanel.WIDTH*4, (int)y, null );
        }
    }

    // Returns x
    // @return x value
    public double getX()
    {
        return x;
    }

    // Returns y
    // @return y value
    public double getY()
    {
        return y;
    }

    // Returns the image
    // @return image Buffered image
    public BufferedImage getImage()
    {
        return image;
    }
}
