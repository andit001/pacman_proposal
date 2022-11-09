package game;

import framework.*;

import java.awt.*;

public class Task2LevelBehaviour extends ComponentBehaviour {
    @Override
    public void Awake() {
        Main.setFrameSize(800, 600);
    }

    public void Start() {
        GameObject background = new GameObject();
        SpriteRenderer backgroundSpriteRenderer = new SpriteRenderer();
        backgroundSpriteRenderer.SetRect(800, 600, Color.BLACK);
        background.AddComponent(backgroundSpriteRenderer);

        GameObject wallTop = new GameObject();
        SpriteRenderer wallTopSpriteRenderer = new SpriteRenderer();
        wallTopSpriteRenderer.SetRect(800, 20, Color.gray);
        wallTopSpriteRenderer.setOrderOfDrawing(1);
        wallTop.AddComponent(wallTopSpriteRenderer);

        GameObject wallBottom = new GameObject();
        SpriteRenderer wallBottomSpriteRenderer = new SpriteRenderer();
        wallBottomSpriteRenderer.SetRect(800, 20, Color.gray);
        wallBottomSpriteRenderer.setOrderOfDrawing(1);
        wallBottom.AddComponent(wallBottomSpriteRenderer);

        wallTop.transform.position = new Vector2(0, 0);
        wallBottom.transform.position = new Vector2(0, 580);

        GameObject enemy = new GameObject();
        SpriteRenderer enemySprite = new SpriteRenderer();
        enemySprite.SetTexture("Assets/Orc-Idle-Sheet.png");
        enemySprite.setTiling(4);
        enemySprite.setOrderOfDrawing(1);
        SpriteAnimator spriteAnimator = new SpriteAnimator();
        spriteAnimator.animationType = SpriteAnimator.AnimationType.Forward;
        enemy.AddComponent(enemySprite);
        enemy.AddComponent(spriteAnimator);
        enemy.transform.position = new Vector2(20, 560);

        GameObject redbull = new GameObject();
        SpriteRenderer redbullSprite = new SpriteRenderer();
        redbullSprite.SetTexture("Assets/redbull.png");
        redbull.AddComponent(redbullSprite);
        redbullSprite.setOrderOfDrawing(1);
        redbull.transform.position = new Vector2(100, 570);

        GameObject pizza = new GameObject();
        SpriteRenderer pizzaSprite = new SpriteRenderer();
        pizzaSprite.SetTexture("Assets/pizza.png");
        pizza.AddComponent(pizzaSprite);
        pizzaSprite.setOrderOfDrawing(1);
        pizza.transform.position = new Vector2(120, 570);

        Main.getScene().AddGameObject(background);
        Main.getScene().AddGameObject(wallTop);
        Main.getScene().AddGameObject(wallBottom);
        Main.getScene().AddGameObject(enemy);
        Main.getScene().AddGameObject(redbull);
        Main.getScene().AddGameObject(pizza);
    }
}
