package game;

import framework.*;

public class PlayerBehaviour extends ComponentBehaviour {
    private LevelBehaviour levelBehaviour;

    private SpriteRenderer spriteRenderer;

    private MovementBehaviour movementBehaviour;

    private double animationSpeed = 0.125;
    private double timeSinceLastAnimationStep = 0.0;

    @Override
    public void Awake() {
        levelBehaviour = (LevelBehaviour) Main.getScene().FindGameObjectOfType(LevelBehaviour.class.getSimpleName());
        spriteRenderer = (SpriteRenderer) GetComponent(SpriteRenderer.class.getSimpleName());
        movementBehaviour = (MovementBehaviour) GetComponent(MovementBehaviour.class.getSimpleName());
    }

    @Override
    public void Start() {
        Vector2 playerStartTile = levelBehaviour.playerStartTile;

        transform.position.x = playerStartTile.x + Game.tileWidth / 2 - spriteRenderer.getWidth() / 2;
        transform.position.y = playerStartTile.y + Game.tileHeight / 2 - spriteRenderer.getHeight() / 2;
    }

    @Override
    public void Update() {
        setDirection();

        timeSinceLastAnimationStep += Time.deltaTime;

        if (timeSinceLastAnimationStep > animationSpeed) {
            var currentTile = spriteRenderer.getCurrentTile();
            if (currentTile < 2) {
                ++currentTile;
            } else {
                currentTile = 0;
            }

            spriteRenderer.setCurrentTile(currentTile);

            timeSinceLastAnimationStep = 0.0;
        }
    }

    public void setDirection() {
        if (movementBehaviour.velocity.x > 0) {
            transform.rotation = 0;
        }
        if (movementBehaviour.velocity.x < 0) {
            transform.rotation = 180;
        }
        if (movementBehaviour.velocity.y > 0) {
            transform.rotation = 90;
        }
        if (movementBehaviour.velocity.y < 0) {
            transform.rotation = 270;
        }
    }
}
