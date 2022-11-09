package game;

import framework.*;

import java.awt.*;

public class Game {
    static final int tileWidth = 40;
    static final int tileHeight = 40;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("Working Directory: " + System.getProperty("user.dir"));

//        createScene();
        createTask2Scene();

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
        pacmanSprite.SetTexture("Assets/pacman_sprite.png");
        pacmanSprite.setTiling(3);
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

    private static void createTask2Scene() {
        Scene scene = new Scene("Task 2");

        GameObject pacman = new GameObject();

        SpriteRenderer spriteRenderer = new SpriteRenderer();
        spriteRenderer.SetTexture("Assets/pacman_sprite.png");
        spriteRenderer.setTiling(3);
        spriteRenderer.setOrderOfDrawing(1);
        SpriteAnimator animator = new SpriteAnimator();
        animator.animationType = SpriteAnimator.AnimationType.Forward;
        pacman.AddComponent(spriteRenderer);
        pacman.AddComponent(animator);
        pacman.AddComponent(new Task2SidewaysMovementBehaviour());

        scene.AddGameObject(pacman);

        GameObject task2Level = new GameObject();
        task2Level.AddComponent(new Task2LevelBehaviour());
        scene.AddGameObject(task2Level);

        GameObject playerPointsLabelObject = new GameObject();
        playerPointsLabelObject.transform.position = new Vector2(50, 50);
        UILabel playerPointsLabel = new UILabel();
        playerPointsLabel.setFont("Assets/Silver.ttf");
        playerPointsLabel.setFontSize(32f);
        playerPointsLabel.setText("Points: 0");
        playerPointsLabel.setColor(Color.yellow);
        playerPointsLabelObject.AddComponent(playerPointsLabel);
        scene.AddGameObject(playerPointsLabelObject);

        GameObject playerLevelLabelObject = new GameObject();
        playerLevelLabelObject.transform.position = new Vector2(50, 80);
        UILabel playerLevelLabel = new UILabel();
        playerLevelLabel.setFont("Assets/Silver.ttf");
        playerLevelLabel.setFontSize(32f);
        playerLevelLabel.setText("Lives: 1");
        playerLevelLabel.setColor(Color.yellow);
        playerLevelLabelObject.AddComponent(playerLevelLabel);
        scene.AddGameObject(playerLevelLabelObject);

        Main.loadScene(scene);
    }
}
