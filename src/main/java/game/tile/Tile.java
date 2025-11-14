package game.tile;

import java.awt.image.BufferedImage;

public class Tile {
    private final TileType type;
    private final BufferedImage image;

    public Tile(TileType type, BufferedImage image) {
        this.type = type;
        this.image = image;
    }

    public boolean isSolid() { return type.solid; }
    public boolean isDangerous() { return type.dangerous; }
    public int getDamage() { return type.damage; }
    public BufferedImage getImage() { return image; }

    public TileType getType() { return type; }
}