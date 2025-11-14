package game.tile;

public enum TileType {
    GRASS(false, false, 0, "tiles/grass.png"),
    WALL(true, false, 0, "tiles/wall.png"),
    WATER(true, false, 0, "tiles/water.png"),
    LAVA(false, true, 8, "tiles/lava.png"),
    SPIKES_GRASS(false, true, 4, "tiles/spikes_on_grass.png"),
    TREE(true, false, 0, "tiles/tree.png");

    public final boolean solid;
    public final boolean dangerous;
    public final int damage;
    public final String imagePath;

    TileType(boolean solid, boolean dangerous, int damage, String imagePath) {
        this.solid = solid;
        this.dangerous = dangerous;
        this.damage = damage;
        this.imagePath = imagePath;
    }
}