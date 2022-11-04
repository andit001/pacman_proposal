package game;

import framework.ComponentBehaviour;
import framework.Main;
import framework.SpriteRenderer;
import framework.Vector2;

public class PlayerBehaviour extends ComponentBehaviour {
    private LevelBehaviour levelBehaviour;

    private SpriteRenderer spriteRenderer;

    @Override
    public void Start() {
        levelBehaviour = (LevelBehaviour) Main.getScene().FindGameObjectOfType(LevelBehaviour.class.getSimpleName());
        spriteRenderer = (SpriteRenderer) GetComponent(SpriteRenderer.class.getSimpleName());

        Vector2 playerStartTile = levelBehaviour.playerStartTile;

        transform.position.x = playerStartTile.x + Game.tileWidth / 2 - spriteRenderer.getWidth() / 2;
        transform.position.y = playerStartTile.y + Game.tileHeight / 2 - spriteRenderer.getHeight() / 2;
    }
}
