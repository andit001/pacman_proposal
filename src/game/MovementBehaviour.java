package game;

import framework.*;
import jdk.jshell.spi.ExecutionControl;

public class MovementBehaviour extends ComponentBehaviour {
    double speed = 100.0;
    public Vector2 velocity;

    private LevelBehaviour levelBehaviour;
    private SpriteRenderer spriteRenderer;

    @Override
    public void Awake() {
        velocity = new Vector2();
        levelBehaviour = (LevelBehaviour) Main.getScene().FindGameObjectOfType(LevelBehaviour.class.getSimpleName());
        spriteRenderer = (SpriteRenderer) GetComponent(SpriteRenderer.class.getSimpleName());
    }

    @Override
    public void Update() {
        Vector2 direction = new Vector2();

        if (Input.isActionRight()) {
            direction.x = 1;
        }

        if (Input.isActionLeft()) {
            direction.x = -1;
        }

        if (Input.isActionUp()) {
            direction.y = -1;
        }

        if (Input.isActionDown()) {
            direction.y = 1;
        }

        // In Pacman one can only move along a single axis (4-way movement)
        if (direction.x != 0.0) {
            direction.y = 0.0;
        }

        velocity = direction.normalized().multiply(speed);

        transform.translate(velocity.multiply(Time.deltaTime));

        double leftDistance = leftDistanceToWall();
        if (leftDistance < 0 && leftDistance > -Game.tileWidth) {
            transform.position.x -= leftDistance;
        }
        double rightDistance = rightDistanceToWall();
        if (rightDistance < 0 && rightDistance > -Game.tileWidth) {
            transform.position.x += rightDistance;
        }
        double downwardsDistance = downwardsDistanceToWall();
        if (downwardsDistance < 0) {
            transform.position.y += downwardsDistance;
        }
        double upwardsDistance = upwardsDistanceToWall();
        if (upwardsDistance < 0) {
            transform.position.y -= upwardsDistance;
        }
    }

    private double upwardsDistanceToWall() {
        double xPointToTest = transform.position.x + Game.tileWidth / 2;
        for (double y = transform.position.y; y >= 0.0; y -= Game.tileHeight) {
            LevelBehaviour.TileType tileType = levelBehaviour.getTileTypeAtPosition(xPointToTest, y);

            if (tileType == LevelBehaviour.TileType.Wall) {
                Rect tileRect = levelBehaviour.getTileRectAtPosition(new Vector2(xPointToTest, y));
                return transform.position.y - tileRect.getYMax();
            }
        }

        return transform.position.y;
    }

    private double downwardsDistanceToWall() {
        double xPointToTest = transform.position.x + Game.tileWidth / 2;
        for (double y = transform.position.y + Game.tileHeight; y < Main.getFrameHeight(); y += Game.tileHeight) {
            LevelBehaviour.TileType tileType = levelBehaviour.getTileTypeAtPosition(xPointToTest, y);

            if (tileType == LevelBehaviour.TileType.Wall) {
                Rect tileRect = levelBehaviour.getTileRectAtPosition(new Vector2(xPointToTest, y));
                return tileRect.getYMin() - transform.position.y - Game.tileHeight;
            }
        }

        return Main.getFrameHeight() - transform.position.y - Game.tileHeight;
    }

    private double leftDistanceToWall() {
        double yPointToTest = transform.position.y + Game.tileHeight / 2;
        for (double x = transform.position.x; x >= 0.0; x -= Game.tileWidth) {
            LevelBehaviour.TileType tileType = levelBehaviour.getTileTypeAtPosition(x, yPointToTest);

            if (tileType == LevelBehaviour.TileType.Wall) {
                Rect tileRect = levelBehaviour.getTileRectAtPosition(new Vector2(x, yPointToTest));
                return transform.position.x - tileRect.getXMax();
            }
        }

        return transform.position.x;
    }

    private double rightDistanceToWall() {
        double yPointToTest = transform.position.y + Game.tileHeight / 2;
        for (double x = transform.position.x + Game.tileWidth; x < Main.getFrameWidth(); x += Game.tileWidth) {
            LevelBehaviour.TileType tileType = levelBehaviour.getTileTypeAtPosition(x, yPointToTest);

            if (tileType == LevelBehaviour.TileType.Wall) {
                Rect tileRect = levelBehaviour.getTileRectAtPosition(new Vector2(x, yPointToTest));
                return tileRect.getXMin() - transform.position.x - Game.tileWidth;
            }
        }

        return Main.getFrameWidth() - transform.position.x - Game.tileWidth;
    }
}
