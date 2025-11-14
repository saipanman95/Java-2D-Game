package game.tile;

import game.FileLoader;
import game.collision.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WallTile extends Tile implements Collidable, TileDetails {
    private final static String WALL = "tiles/wall.png";


    public WallTile(BufferedImage image){
        super(TileType.WATER, image);
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle(0, 0, 32, 32);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isDangerous() {
        return false;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public BufferedImage getImage() {
        return super.getImage();    }
}
