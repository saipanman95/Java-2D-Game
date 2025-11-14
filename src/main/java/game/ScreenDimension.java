package game;

public class ScreenDimension {
    public int screenWidth;
    public int screenHeight;
    public int tileSize;

    public ScreenDimension(
            int screenHeight,
            int screenWidth,
            int tileSize
    ){
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.tileSize = tileSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getTileSize() {
        return tileSize;
    }
}
