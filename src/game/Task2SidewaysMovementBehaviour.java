package game;

import framework.*;

public class Task2SidewaysMovementBehaviour extends ComponentBehaviour {
    private int frameWidth;
    private int frameHeight;

    private int tileWidth;

    public double speed = 100.0;

    private SpriteRenderer spriteRenderer;

    @Override
    public void Awake() {
        spriteRenderer = (SpriteRenderer) GetComponent(SpriteRenderer.class.getSimpleName());
    }

    @Override
    public void Start() {
        frameWidth = Main.getFrameWidth();
        frameHeight = Main.getFrameHeight();
        tileWidth = spriteRenderer.getTileWidth();

        transform.position = new Vector2(-tileWidth, 280);
    }
    @Override
    public void Update() {
        transform.position.x += speed * Time.deltaTime;

        if (transform.position.x > frameWidth + tileWidth) {
            transform.position.x = -tileWidth;
        }
    }
}
