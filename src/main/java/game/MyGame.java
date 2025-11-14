package game;

import javax.swing.*;
import java.awt.*;

public class MyGame {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //window.setSize(new Dimension(500, 600));
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        gamePanel.startGameGameThread();
        window.setTitle("2D Adventure"); // changing the name
        window.setLocationRelativeTo(null); // will display the window center of the screen.
        window.setVisible(true);          // make visible LAST
        gamePanel.requestFocusInWindow();        // ensure GamePanel gets keyboard focus
        gamePanel.startGameGameThread();
    }
}
