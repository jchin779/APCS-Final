package GameState;

import TileMap.*;

import java.awt.*;


public class Level1State extends GameState
{

    private TileMap tileMap;

    private Background bg;


    public Level1State( GameStateManager gsm )
    {
        this.gsm = gsm;
        init();
    }


    public void init()
    {

        tileMap = new TileMap( 30 );
        tileMap.loadTiles( "/Tilesets/tile.gif" );
        tileMap.loadMap( "/Maps/maap.map" );
        tileMap.setPosition( 0, 0 );

        bg = new Background( "/Backgrounds/bg.jpg", 0.1 );

    }


    public void update()
    {
    }


    public void draw( Graphics2D g )
    {

        // clear bg
        bg.draw( g );

        // draw tilemap
        tileMap.draw( g );

    }


    public void keyPressed( int k )
    {
    }


    public void keyReleased( int k )
    {
    }

}
