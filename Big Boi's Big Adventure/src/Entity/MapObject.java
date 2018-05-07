package Entity;

import Main.GamePanel;
import TileMap.TileMap;
import TileMap.Tile;

import java.awt.Rectangle;


public abstract class MapObject
{

    // tile stuff
    protected TileMap tileMap;

    protected int tileSize;

    protected double xmap;

    protected double ymap;

    // position and vector
    protected double x;

    protected double y;

    protected double dx;

    protected double dy;

    // dimensions
    protected int width;

    protected int height;

    // collision box
    protected int cwidth;

    protected int cheight;

    // collision
    protected int currRow;

    protected int currCol;

    protected double xdest;

    protected double ydest;

    protected double xtemp;

    protected double ytemp;

    protected boolean topLeft;

    protected boolean topRight;

    protected boolean bottomLeft;

    protected boolean bottomRight;

    // animation
    /**
     * TODO un comment
     */
//    protected Animation animation;

    protected int currentAction;

    protected int previousAction;

    protected boolean facingRight;

    // movement
    protected boolean left;

    protected boolean right;

    protected boolean up;

    protected boolean down;

    protected boolean jumping;

    protected boolean falling;

    // movement attributes
    protected double moveSpeed;

    protected double maxSpeed;

    protected double stopSpeed;

    protected double fallSpeed;

    protected double maxFallSpeed;

    protected double jumpStart;

    protected double stopJumpSpeed;


    // constructor
    public MapObject( TileMap tm )
    {
        tileMap = tm;
        tileSize = tm.getTileSize();
    }

    // Checks if the two objects intersect
    public boolean intersects( MapObject o )
    {
        Rectangle r1 = getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.intersects( r2 );
    }

    // Returns a new rectangle
    public Rectangle getRectangle()
    {
        return new Rectangle( (int)x - cwidth, (int)y - cheight, cwidth, cheight );
    }

    // Calculates the corners of the screen
    public void calculateCorners( double x, double y )
    {

        int leftTile = (int)( x - cwidth / 2 ) / tileSize;
        int rightTile = (int)( x + cwidth / 2 - 1 ) / tileSize;
        int topTile = (int)( y - cheight / 2 ) / tileSize;
        int bottomTile = (int)( y + cheight / 2 - 1 ) / tileSize;

        int tl = tileMap.getType( topTile, leftTile );
        int tr = tileMap.getType( topTile, rightTile );
        int bl = tileMap.getType( bottomTile, leftTile );
        int br = tileMap.getType( bottomTile, rightTile );

        topLeft = tl == Tile.BLOCKED;
        topRight = tr == Tile.BLOCKED;
        bottomLeft = bl == Tile.BLOCKED;
        bottomRight = br == Tile.BLOCKED;

    }

    // Checks for collisions on the map with tiles
    public void checkTileMapCollision()
    {

        currCol = (int)x / tileSize;
        currRow = (int)y / tileSize;

        xdest = x + dx;
        ydest = y + dy;

        xtemp = x;
        ytemp = y;

        calculateCorners( x, ydest );
        if ( dy < 0 )
        {
            if ( topLeft || topRight )
            {
                dy = 0;
                ytemp = currRow * tileSize + cheight / 2;
            }
            else
            {
                ytemp += dy;
            }
        }
        if ( dy > 0 )
        {
            if ( bottomLeft || bottomRight )
            {
                dy = 0;
                falling = false;
                ytemp = ( currRow + 1 ) * tileSize - cheight / 2;
            }
            else
            {
                ytemp += dy;
            }
        }

        calculateCorners( xdest, y );
        if ( dx < 0 )
        {
            if ( topLeft || bottomLeft )
            {
                dx = 0;
                xtemp = currCol * tileSize + cwidth / 2;
            }
            else
            {
                xtemp += dx;
            }
        }
        if ( dx > 0 )
        {
            if ( topRight || bottomRight )
            {
                dx = 0;
                xtemp = ( currCol + 1 ) * tileSize - cwidth / 2;
            }
        }

        if ( !falling )
        {
            calculateCorners( x, ydest + 1 );
            if ( !bottomLeft && !bottomRight )
            {
                falling = true;
            }
        }

    }

    // Returns the x value
    public int getx()
    {
        return (int)x;
    }

    // Returns the y value
    public int gety()
    {
        return (int)y;
    }

    // Returns the width 
    public int getWidth()
    {
        return width;
    }

    // Returns the height
    public int getHeight()
    {
        return height;
    }

    // Returns the width
    public int getCWidth()
    {
        return cwidth;
    }

    // Returns collision box height
    public int getCHeight()
    {
        return cheight;
    }

    // Sets the position of x and y
    public void setPosition( double x, double y )
    {
        this.x = x;
        this.y = y;
    }

    // Sets the vectors dx and dy
    public void setVector( double dx, double dy )
    {
        this.dx = dx;
        this.dy = dy;
    }

    // Sets the map positions
    public void setMapPosition()
    {
        xmap = tileMap.getx();
        ymap = tileMap.gety();
    }

    // Sets left movement
    public void setLeft( boolean b )
    {
        left = b;
    }

    // Sets right movement
    public void setRight( boolean b )
    {
        right = b;
    }

    // Sets upwards movement
    public void setUp( boolean b )
    {
        up = b;
    }

    // Sets downwards movement
    public void setDown( boolean b )
    {
        down = b;
    }

    // Sets jumping
    public void setJumping( boolean b )
    {
        jumping = b;
    }

    // Checks if a point is not on the screen
    public boolean notOnScreen()
    {
        return x + xmap + width < 0 || x + xmap - width > GamePanel.WIDTH || y + ymap + height < 0
            || y + ymap - height > GamePanel.HEIGHT;
    }

}
