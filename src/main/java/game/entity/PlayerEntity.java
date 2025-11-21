package game.entity;

import game.FileLoader;
import game.GamePanel;
import game.KeyHandler;
import game.tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class PlayerEntity extends Entity {

    private final GamePanel gp;
    private final KeyHandler keyH;
    private final Map<String, BufferedImage> playerImageMap = new HashMap<>();

    private int animIndex = 0;
    private int animCounter = 0;

    public PlayerEntity(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        loadPlayerImages();
    }

    private void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    private void loadPlayerImages() {
        FileLoader iml = new FileLoader();
        playerImageMap.put("up", iml.loadImage("player/up-stand-still.png"));
        playerImageMap.put("up1", iml.loadImage("player/up-right-step.png"));
        playerImageMap.put("up2", iml.loadImage("player/up-left-step.png"));
        playerImageMap.put("down", iml.loadImage("player/down-stand-still.png"));
        playerImageMap.put("down1", iml.loadImage("player/down-right-step.png"));
        playerImageMap.put("down2", iml.loadImage("player/down-left-step.png"));
        playerImageMap.put("left", iml.loadImage("player/left-stand-still.png"));
        playerImageMap.put("left1", iml.loadImage("player/left-left-step.png"));
        playerImageMap.put("left2", iml.loadImage("player/left-right-step.png"));
        playerImageMap.put("right", iml.loadImage("player/right-stand-still.png"));
        playerImageMap.put("right1", iml.loadImage("player/right-right-step.png"));
        playerImageMap.put("right2", iml.loadImage("player/right-left-step.png"));
    }

    @Override
    public void update() {
        int dx = 0, dy = 0;
        boolean moving = false;

        if (keyH.upPressed)    { dy -= speed; direction = "up";    moving = true; }
        if (keyH.downPressed)  { dy += speed; direction = "down";  moving = true; }
        if (keyH.leftPressed)  { dx -= speed; direction = "left";  moving = true; }
        if (keyH.rightPressed) { dx += speed; direction = "right"; moving = true; }

        if (moving) {
            // Y first
            if (!gp.collisionChecker.willCollide(this, 0, dy)) y += dy;
            // then X
            if (!gp.collisionChecker.willCollide(this, dx, 0)) x += dx;
        }

        x = Math.max(0, Math.min(x, gp.screenWidth  - gp.tileSize));
        y = Math.max(0, Math.min(y, gp.screenHeight - gp.tileSize));

        tick(moving);
    }


    /** Checks for collisions with solid or dangerous tiles. */
    private boolean willCollide(int dx, int dy) {
        Rectangle futureBounds = getBounds();
        futureBounds.translate(dx, dy);

        int leftCol = futureBounds.x / gp.tileSize;
        int rightCol = (futureBounds.x + futureBounds.width - 1) / gp.tileSize;
        int topRow = futureBounds.y / gp.tileSize;
        int bottomRow = (futureBounds.y + futureBounds.height - 1) / gp.tileSize;

        for (int row = topRow; row <= bottomRow; row++) {
            for (int col = leftCol; col <= rightCol; col++) {
                Tile tile = gp.tileManager.mapTiles[col][row];
                if (tile == null) continue;

                if (tile.isSolid()) {
                    return true;
                }
                if (tile.isDangerous()) {
                    takeDamage(tile.getDamage());
                }
            }
        }

        return false;
    }

    private void tick(boolean moving) {
        if (moving) {
            if (++animCounter >= 12) {
                animCounter = 0;
                animIndex = (animIndex + 1) % 3;
            }
        }
    }

    private BufferedImage currentFrame() {
        return playerImageMap.getOrDefault(direction + animIndex, playerImageMap.get(direction));
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2.drawImage(currentFrame(), x, y, gp.tileSize, gp.tileSize, null);

        // Optional: draw hitbox for debugging
        // g2.setColor(Color.RED);
        // g2.draw(getBounds());
    }
}
