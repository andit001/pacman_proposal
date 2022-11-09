package framework;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SpriteRenderer extends ComponentBehaviour {
    private BufferedImage bufferedImage;
    private SwingScene swingScene;

    /**
     * 4 layers are supported in the range [0, 3].
     * Layer 0 is drawn first then layer 1 and so on.
     */
    private int orderOfDrawing = 0;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    private int width;
    private int height;
    private Color color = Color.BLACK;

    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    enum DrawMode {
        Simple,
        Tiled
    }

    private int numberOfTiles;
    private int currentTile;

    private int tileWidth;
    private int tileHeight;
    public DrawMode drawMode;

    public SpriteRenderer() {
        drawMode = DrawMode.Simple;
    }
    public void Start() {
        swingScene = Main.swingScene;
        swingScene.Add(this);
    }

    /**
     * The width of the image set with SetImage must be dividable by numberOfTile.
     * @param numberOfTiles Number of tiles on the image.
     */
    public void setTiling(int numberOfTiles) {
        assert(bufferedImage != null);

        this.numberOfTiles = numberOfTiles;
        drawMode = DrawMode.Tiled;
        currentTile = 0;

        // using a very simple approach for now, i.e. not using multiple rows.
        tileWidth = getWidth() / numberOfTiles;
        tileHeight = getHeight();
    }

    /**
     * Sets the next tile to draw.
     * @param tile Next tile to draw in [0, numberOfTiles).
     */
    public void setCurrentTile(int tile) {
        assert(tile >= 0 && tile < numberOfTiles);
        if (tile != currentTile) {
            currentTile = tile;
        }
    }

    public int getCurrentTile() {
        return currentTile;
    }

    public void Render(Graphics2D graphics2D) {
        if (bufferedImage == null) {
            RenderRect(graphics2D);
        } else {
            if (drawMode == DrawMode.Simple) {
                RenderImage(graphics2D);
            } else {
                RenderTileImage(graphics2D);
            }
        }
    }

    private void RenderTileImage(Graphics2D graphics2D) {
        int sourceImageX1 = tileWidth * currentTile;
        int sourceImageX2 = sourceImageX1 + tileWidth;
        int sourceImageY1 = 0;
        int sourceImageY2 = tileHeight;

        int destinationX1 = (int)Math.round(transform.position.x);
        int destinationX2 = (int)Math.round(transform.position.x) + tileWidth;
        int destinationY1 = (int)Math.round(transform.position.y);
        int destinationY2 = (int)Math.round(transform.position.y) + tileHeight;

        AffineTransform reset = graphics2D.getTransform();
        if (transform.rotation != 0.0) {
            reset.rotate(0, 0, 0);
            AffineTransform affineTransform = new AffineTransform();
            int anchorX = destinationX1 + tileWidth / 2;
            int anchorY = destinationY1 + tileHeight / 2;
            affineTransform.rotate(Math.toRadians(transform.rotation), anchorX, anchorY);

            graphics2D.setTransform(affineTransform);
        }
        graphics2D.drawImage(
                bufferedImage,
                destinationX1, destinationY1, destinationX2, destinationY2,
                sourceImageX1, sourceImageY1, sourceImageX2, sourceImageY2,
                null
        );
        graphics2D.setTransform(reset);
    }

    private void RenderImage(Graphics2D graphics2D) {
        int x = (int)Math.round(transform.position.x);
        int y = (int)Math.round(transform.position.y);
        graphics2D.drawImage(bufferedImage, x, y, null);
    }

    private void RenderRect(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        int x = (int)Math.round(transform.position.x);
        int y = (int)Math.round(transform.position.y);
        graphics2D.fillRect(x, y, width, height);
    }

    public void Destroy() {
        swingScene.Remove(this);
    }

    public void SetTexture(String path) {
        bufferedImage = null;
        try {
            InputStream inputStream = getClass().getResourceAsStream("/" + path);
            bufferedImage = ImageIO.read(inputStream);
//            bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.err.println("Error: Couldn't load file " + path);
        }

        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
    }

    public void SetRect(int width, int height, Color color) {
        bufferedImage = null;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * 4 layers are supported in the range [0, 3].
     * Layer 0 is drawn first then layer 1 and so on.
     */
    public int getOrderOfDrawing() {
        return orderOfDrawing;
    }

    /**
     * 4 layers are supported in the range [0, 3].
     * Layer 0 is drawn first then layer 1 and so on.
     */
    public void setOrderOfDrawing(int orderOfDrawing) {
        assert(orderOfDrawing >= 0 && orderOfDrawing < 4);
        this.orderOfDrawing = orderOfDrawing;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }
}
