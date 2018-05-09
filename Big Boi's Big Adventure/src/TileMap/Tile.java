package TileMap;

import java.awt.image.BufferedImage;


public class Tile
{
    private BufferedImage image;

    private int type;

    // tile types
    public static final int NORMAL = 0;

    public static final int BLOCKED = 1;

    // Constructor
    // @param image Buffered Image
    // @param type Int tile type
    public Tile( BufferedImage image, int type )
    {
        this.image = image;
        this.type = type;
    }

    // Returns the image
    // @return image Buffered Image
    public BufferedImage getImage()
    {
        return image;
    }

    // Returns the tile type
    // @return type Tile type
    public int getType()
    {
        return type;
    }
}
