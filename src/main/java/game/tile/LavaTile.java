package game.tile;

import game.FileLoader;
import game.collision.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LavaTile extends Tile implements Collidable, TileDetails {
    private final static String GRASS = "tiles/lava.png";

    public LavaTile(BufferedImage image){
        super(TileType.LAVA, image);
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
        return 10;
    }

    @Override
    public BufferedImage getImage() {
        return super.getImage();    }
}
