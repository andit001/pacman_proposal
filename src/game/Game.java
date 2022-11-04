package game;

import framework.GameObject;
import framework.Main;
import framework.Scene;
import framework.SpriteRenderer;

public class Game {
    static final int tileWidth = 40;
    static final int tileHeight = 40;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        createScene();

        Main.Run("Pacman");
    }

    private static void createScene() {
        Scene scene = new Scene("Level 1");

        /// Create the yellow moving game object.
        GameObject pacman = new GameObject();
        // Add the example TimerPrinterBehaviour.
        pacman.AddComponent(new TimePrinterBehaviour());
        // Setup SpriteRenderer with a Texture.
        SpriteRenderer pacmanSprite = new SpriteRenderer();
        pacmanSprite.SetTexture("Assets/pacman_test.png");
        // Make sure the object is painted after the level.
        pacmanSprite.setOrderOfDrawing(1);
        pacman.AddComponent(pacmanSprite);
        pacman.AddComponent(new MovementBehaviour());
        pacman.AddComponent(new PlayerBehaviour());

        scene.AddGameObject(pacman);
        ////////////////////////////////////////

        /// Create the level object.
        GameObject level = new GameObject();
        level.AddComponent(new LevelBehaviour("Assets/level1.txt"));

        scene.AddGameObject(level);
        ////////////////////////////////////////

        Main.loadScene(scene);
    }
}
