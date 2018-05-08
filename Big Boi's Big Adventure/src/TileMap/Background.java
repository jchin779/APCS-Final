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
    public void setPosition( double x, double y )
    {
        this.x = ( x * moveScale ) % GamePanel.WIDTH;
        this.y = ( y * moveScale ) % GamePanel.HEIGHT;
    }

    // Sets the two vectors dx and dy
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
    public double getX()
    {
        return x;
    }

    // Returns y
    public double getY()
    {
        return y;
    }

    // Returns the image
    public BufferedImage getImage()
    {
        return image;
    }
}
