package game.tile;

import game.collision.Collidable;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SpikesGrassTile extends Tile implements Collidable, TileDetails {

    public SpikesGrassTile(BufferedImage image) {
        super(TileType.SPIKES_GRASS, image);
    }

    @Override
    public Rectangle getBounds() {
        // You can set this dynamically later based on tile position
        return new Rectangle(0, 0, 32, 32);
    }

    @Override
    public boolean isSolid() {
        return super.isSolid();
    }

    @Override
    public boolean isDangerous() {
        return super.isDangerous();
    }

    @Override
    public int getDamage() {
        return super.getDamage();
    }

    @Override
    public BufferedImage getImage() {
        return super.getImage();
    }
}
