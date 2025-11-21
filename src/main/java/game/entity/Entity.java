package game.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    // Basic movement and render data
    public int x;
    public int y;
    public int speed;
    public String direction;

    // Sprite frames (optional: subclasses can manage their own image maps)
    public BufferedImage up, up1, up2, down, down1, down2;
    public BufferedImage right, right1, right2, left, left1, left2;

    // Collision box
    public Rectangle solidArea;
    public boolean collisionOn = false;

    // Combat / health attributes
    protected int health = 100;
    protected int maxHealth = 100;
    protected boolean alive = true;

    public Entity() {
        // Default solid area (can be customized per subclass)
        solidArea = new Rectangle(8, 16, 16, 16); // example offset and size
    }

    // Core reusable behavior methods
    public void takeDamage(int amount) {
        health -= amount;
        if (health <= 0) {
            health = 0;
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public Rectangle getBounds() {
        return new Rectangle(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
    }

    // Abstract methods for subclasses
    public abstract void update();
    public abstract void draw(Graphics2D g2);
}
