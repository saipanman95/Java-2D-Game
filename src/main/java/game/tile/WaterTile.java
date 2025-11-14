package game.tile;

import game.FileLoader;
import game.collision.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WaterTile extends Tile implements Collidable, TileDetails {
    private final static String WATER = "tiles/water.png";

    public WaterTile(BufferedImage image){
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
        return true;
    }

    @Override
    public int getDamage() {
        return 1;
    }

    @Override
    public BufferedImage getImage() {
        return super.getImage();    }
}
