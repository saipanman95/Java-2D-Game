package game.tile;

import game.FileLoader;
import game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class TileManager {

    GamePanel gp;
    private final Map<TileType, BufferedImage> tileImages = new EnumMap<>(TileType.class);
    public Tile[][] mapTiles;
    FileLoader loader = new FileLoader();

    public TileManager(GamePanel gp) {
        this.gp = gp;
        mapTiles = new Tile[gp.maxScreenCol][gp.maxScreenRow];
        loadTileImages();
        loadMap("maps/map-2.data");
    }

    private void loadTileImages() {
        for (TileType type : TileType.values()) {
            tileImages.put(type, loader.loadImage(type.imagePath));
        }
    }

    private Tile createTile(TileType type) {
        if (type == TileType.SPIKES_GRASS) {
            return new SpikesGrassTile(tileImages.get(type));
        } else if(type == TileType.TREE) {
            return new TreeTile(tileImages.get(type));
        } else if( type == TileType.LAVA) {
            return new LavaTile(tileImages.get(type));
        } else if(type == TileType.WALL) {
            return new WallTile(tileImages.get(type));
        } else if (type == TileType.GRASS) {
            return new GrassTile(tileImages.get(type));
        } else if (type == TileType.WATER){
            return new WaterTile(tileImages.get(type));
        } else {
            return new Tile(type, tileImages.get(type));
        }
    }

    public void loadMap(String mapResource) {
        List<String> lines = loader.loadMapLines(mapResource);
        int row = 0;

        for (String raw : lines) {
            if (row >= gp.maxScreenRow) break;
            String line = raw.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;

            String[] tokens = line.split("\\s+");
            for (int col = 0; col < gp.maxScreenCol; col++) {
                int id = Integer.parseInt(tokens[col]);
                TileType type = TileType.values()[id]; // Use enum index for mapping
                mapTiles[col][row] = createTile(type);
            }
            row++;
        }
    }

    public void draw(Graphics2D g2) {
        for (int row = 0; row < gp.maxScreenRow; row++) {
            for (int col = 0; col < gp.maxScreenCol; col++) {
                Tile tile = mapTiles[col][row];
                if (tile != null) {
                    int x = col * gp.tileSize;
                    int y = row * gp.tileSize;
                    g2.drawImage(tile.getImage(), x, y, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}
