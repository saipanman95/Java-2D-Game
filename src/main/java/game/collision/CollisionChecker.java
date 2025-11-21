package game.collision;

import game.GamePanel;
import game.entity.Entity;
import game.tile.Tile;

import java.awt.*;

public class CollisionChecker {
    private final GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Checks if an entity will collide with solid or dangerous tiles based on its next move.
     * @param entity the entity to check
     */
    public void checkTile(Entity entity) {
        // Predict future bounds
        Rectangle futureBounds = entity.getBounds();

        switch (entity.direction) {
            case "up" -> futureBounds.y -= entity.speed;
            case "down" -> futureBounds.y += entity.speed;
            case "left" -> futureBounds.x -= entity.speed;
            case "right" -> futureBounds.x += entity.speed;
        }

        int leftCol = futureBounds.x / gp.tileSize;
        int rightCol = (futureBounds.x + futureBounds.width - 1) / gp.tileSize;
        int topRow = futureBounds.y / gp.tileSize;
        int bottomRow = (futureBounds.y + futureBounds.height - 1) / gp.tileSize;

        entity.collisionOn = false;

        // Loop through overlapping tiles
        for (int row = topRow; row <= bottomRow; row++) {
            for (int col = leftCol; col <= rightCol; col++) {
                if (col < 0 || row < 0 || col >= gp.maxScreenCol || row >= gp.maxScreenRow)
                    continue;

                Tile tile = gp.tileManager.mapTiles[col][row];
                if (tile == null) continue;

                if (tile.isSolid()) {
                    entity.collisionOn = true;
                    return;
                }

                if (tile.isDangerous()) {
                    entity.takeDamage(tile.getDamage());
                }
            }
        }
    }

    /**
     * Checks collision between two entities (e.g., player vs enemy).
     * @param e1 first entity
     * @param e2 second entity
     * @return true if they intersect
     */
    public boolean checkEntityCollision(Entity e1, Entity e2) {
        if (e1 == null || e2 == null) return false;

        Rectangle r1 = e1.getBounds();
        Rectangle r2 = e2.getBounds();
        return r1.intersects(r2);
    }

    /**
     * Checks if an entity collides with any entity in an array.
     */
    public Entity checkEntityArrayCollision(Entity target, Entity[] entities) {
        for (Entity e : entities) {
            if (e != null && e != target && checkEntityCollision(target, e)) {
                return e;
            }
        }
        return null;
    }

    public boolean willCollide(Entity entity, int dx, int dy) {

        Rectangle future = entity.getBounds();  // already includes offsets
        future.translate(dx, dy);

        int leftCol   = future.x / gp.tileSize;
        int rightCol  = (future.x + future.width  - 1) / gp.tileSize;
        int topRow    = future.y / gp.tileSize;
        int bottomRow = (future.y + future.height - 1) / gp.tileSize;

        for (int r = topRow; r <= bottomRow; r++) {
            for (int c = leftCol; c <= rightCol; c++) {
                if (c < 0 || r < 0 || c >= gp.maxScreenCol || r >= gp.maxScreenRow) continue;
                var tile = gp.tileManager.mapTiles[c][r];
                if (tile == null) continue;
                if (tile.isSolid()) return true;
                if (tile.isDangerous()) entity.takeDamage(tile.getDamage());
            }
        }
        return false;
    }
}
