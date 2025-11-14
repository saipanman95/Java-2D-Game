package game.entity;

import game.FileLoader;
import game.GamePanel;
import game.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class PlayerEntity extends Entity {
    public final GamePanel gp;
    public final KeyHandler keyH;

    private static final String PLAYER_DOWN_LEFT_STEP = "player/down-left-step.png";
    private static final String PLAYER_DOWN_RIGHT_STEP = "player/down-right-step.png";
    private static final String PLAYER_DOWN_STAND_STILL = "player/down-stand-still.png";
    private static final String PLAYER_UP_RIGHT_STEP = "player/up-right-step.png";
    private static final String PLAYER_UP_LEFT_STEP = "player/up-left-step.png";
    private static final String PLAYER_UP_STAND_STILL = "player/up-stand-still.png";
    private static final String PLAYER_LEFT_LEFT_STEP = "player/left-left-step.png";
    private static final String PLAYER_LEFT_RIGHT_STEP = "player/left-right-step.png";
    private static final String PLAYER_LEFT_STAND_STILL = "player/left-stand-still.png";
    private static final String PLAYER_RIGHT_LEFT_STEP = "player/right-right-step.png";
    private static final String PLAYER_RIGHT_RIGHT_STEP = "player/right-left-step.png";
    private static final String PLAYER_RIGHT_STAND_STILL = "player/right-stand-still.png";

    final Map<String, BufferedImage> playerImageMap = new HashMap<>();

    private int animIndex = 0;
    private int animCounter = 0;     // counts game updates

    // health
    private int health;
    private int lives;

    public PlayerEntity(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gp = gamePanel;
        this.keyH = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        playerSpeed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        FileLoader iml = new FileLoader();
        playerImageMap.put("up", iml.loadImage(PLAYER_UP_STAND_STILL));
        playerImageMap.put("up1", iml.loadImage(PLAYER_UP_RIGHT_STEP));
        playerImageMap.put("up2", iml.loadImage(PLAYER_UP_LEFT_STEP));
        playerImageMap.put("down", iml.loadImage(PLAYER_DOWN_STAND_STILL));
        playerImageMap.put("down1", iml.loadImage(PLAYER_DOWN_RIGHT_STEP));
        playerImageMap.put("down2", iml.loadImage(PLAYER_DOWN_LEFT_STEP));
        playerImageMap.put("right", iml.loadImage(PLAYER_RIGHT_STAND_STILL));
        playerImageMap.put("right1", iml.loadImage(PLAYER_RIGHT_RIGHT_STEP));
        playerImageMap.put("right2", iml.loadImage(PLAYER_RIGHT_LEFT_STEP));
        playerImageMap.put("left", iml.loadImage(PLAYER_LEFT_STAND_STILL));
        playerImageMap.put("left1", iml.loadImage(PLAYER_LEFT_LEFT_STEP));
        playerImageMap.put("left2", iml.loadImage(PLAYER_LEFT_RIGHT_STEP));
    }

    public void update() {
        //default state of image
        boolean moving = false;

        if (keyH.upPressed) {
            y -= playerSpeed;
            moving = true;
            direction = "up";
        }
        if (keyH.downPressed) {
            y += playerSpeed;
            moving = true;
            direction = "down";
        }
        if (keyH.leftPressed) {
            x -= playerSpeed;
            moving = true;
            direction = "left";
        }
        if (keyH.rightPressed) {
            x += playerSpeed;
            moving = true;
            direction = "right";
        }

        // keep player on screen (optional clamp)
        x = Math.max(0, Math.min(x, gp.screenWidth - gp.tileSize));
        y = Math.max(0, Math.min(y, gp.screenHeight - gp.tileSize));

        /*
          moving is boolean variable that is set if key press occurs
          this will cause the underlying PlayerEntity object to change
          motion of the image by swapping which one will be displayed
          when player.currentFrame() is called in the paintComponent(...)
          method.
         */
        tick(moving);
    }

    public void draw(Graphics2D g2) {
        // keep pixel art crisp when scaled
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        BufferedImage currentImageFrame = currentFrame();
        g2.drawImage(currentImageFrame, x, y, gp.tileSize, gp.tileSize, null);

    }

    // Example getter
    public void tick(boolean moving) {
        if (moving) {
            if (++animCounter >= 12) {
                animCounter = 0;
                animIndex = (animIndex + 1) % 3;
            }
        }
    }

    public BufferedImage currentFrame() {
        if (animIndex == 0) {
            return playerImageMap.get(direction);
        } else if (animIndex == 1) {
            return playerImageMap.get(direction + animIndex);
        } else if (animIndex == 2) {
            return playerImageMap.get(direction + animIndex);
        } else {
            return playerImageMap.get(direction);
        }
    }
}
