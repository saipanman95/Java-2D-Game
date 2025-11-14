package game;

import game.entity.PlayerEntity;
import game.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable { //extending JPanel makes GamePanel a subclass
    //screen settings
    public final int originalTileSize = 32; // this will be tile size
    public final int scale = 2;
    public final int tileSize = originalTileSize * scale; // 64x64
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //64 * 16 = 1024 screen pixel width
    public final int screenHeight = tileSize * maxScreenRow; // 64 * 12 = 768 screen pixel height


    //FPS
    int fps = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    private final PlayerEntity player = new PlayerEntity(this, keyH);

    TileManager tileManager = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        //adding keyhandler after creating new class
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameGameThread() {
        gameThread = new Thread(this); // this --> is the GamePanel
        gameThread.start();
    }

    @Override
    public void run() {
        //this calculation is needed for drawing the screen
        double drawInterval = 1000000000 / fps; // used to draw the screen 60 times per second
        double nextDrawTime = System.nanoTime() + drawInterval;
        //going to create the game loop which is the core of the game.
        while (gameThread != null) {

//            System.out.println("Game is running");
            //1. update information character position
            update();
            //2. draw the screen with updated information
            repaint();

            //sleep method
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                //need to convert nano seconds to milliseconds
                remainingTime /= 1_000_000.0; // ns -> ms
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime = nextDrawTime + drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //Graphics class is your paintbrush

        super.paintComponent(g);
        var g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
