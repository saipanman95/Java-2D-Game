package game.collision;

import java.awt.*;

public interface Collidable {
    Rectangle getBounds();
    boolean isSolid();
    boolean isDangerous();
    int getDamage();
}
